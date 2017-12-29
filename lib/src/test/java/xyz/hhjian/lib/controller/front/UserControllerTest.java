package xyz.hhjian.lib.controller.front;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import xyz.hhjian.lib.entity.bo.LoginBO;
import xyz.hhjian.lib.utils.JsonUtil;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <p>测试用户控制器</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Value("${security.jwt.token-header-name}")
    private String tokenHeaderName;
    @Value("${security.jwt.tokenHead}")
    private String tokenHead;
    private static Long userId;
    private static String token;
    private static Long orderId;

    private static String[] bookIds = new String[]{"65123489514", "65123489515"};

    @Test
    public void a_login() throws Exception {
        // login get userId token
        String username = "hao";
        String password = "123456";
        LoginBO authForm = new LoginBO();
        authForm.setUsername(username);
        authForm.setPassword(password);
        MvcResult mvcResult = mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JsonUtil.toJson(authForm)))
                .andDo(print())
                .andExpect(content().json("{'errcode':0}"))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Map map = JsonUtil.toMap(response);
        Map data = (Map) map.get("data");
        userId = (Long) data.get("userId");
        token = tokenHead + data.get("token");
    }

    @Test
    public void b_getUser() throws Exception {
        mockMvc.perform(
                get("/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header(tokenHeaderName, token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void c_applyBorrowBook() throws Exception {
        mockMvc.perform(
                put("/users/{userId}/books/{bookId}", userId, bookIds[0])
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header(tokenHeaderName, token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void d_cancelBorrowBook() throws Exception {
        orderId = 1000000L;
        mockMvc.perform(
                delete("/users/{userId}/orders/{orderId}", userId, orderId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header(tokenHeaderName, token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void e_reapplyBorrowBook() throws Exception {
        mockMvc.perform(
                put("/users/{userId}/orders/{orderId}", userId, orderId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header(tokenHeaderName, token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void f_listOrder() throws Exception {
        mockMvc.perform(
                get("/users/{userId}/orders/list", userId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header(tokenHeaderName, token))
                .andDo(print())
                .andExpect(status().isOk());
    }

}