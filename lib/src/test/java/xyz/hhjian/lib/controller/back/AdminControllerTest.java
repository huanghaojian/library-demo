package xyz.hhjian.lib.controller.back;

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
import xyz.hhjian.lib.entity.domain.User;
import xyz.hhjian.lib.entity.bo.LoginBO;
import xyz.hhjian.lib.utils.JsonUtil;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <p>测试管理员控制器</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${security.jwt.tokenHeaderName}")
    private String tokenHeaderName;
    @Value("${security.jwt.tokenHead}")
    private String tokenHead;
    private static Long rootId;
    private static String token;
    private static Long adminId;

    @Test
    public void a_rootLogin() throws Exception {
        // 超级管理员登录获取id和token
        String username = "hhjian";
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
        // 从response获取
        String response = mvcResult.getResponse().getContentAsString();
        Map map = JsonUtil.toMap(response);
        Map data = (Map) map.get("data");
        rootId = (Long) data.get("userId");
        token = tokenHead + data.get("token");
    }

    @Test
    public void b_saveAdmin() throws Exception {
        User admin = new User();
        admin.setUsername("admin");
        admin.setNickname("图书馆管理员");
        admin.setPassword("123456");
        admin.setTel("13895642569");
        MvcResult mvcResult = mockMvc.perform(post("/admins/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header(tokenHeaderName, token)
                .content(JsonUtil.toJson(admin)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        // 获取新增管理员Id
        String response = mvcResult.getResponse().getContentAsString();
        Map map = JsonUtil.toMap(response);
        Map data = (Map) map.get("data");
        adminId = (Long) data.get("userId");
    }


    @Test
    public void c_updateAdmin() throws Exception {
        //TODO
    }

    @Test
    public void d_getAdmin() throws Exception {
        mockMvc.perform(get("/admins/{adminId}", adminId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header(tokenHeaderName, token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void e_removeAdmin() throws Exception {
        mockMvc.perform(delete("/admins/{adminId}", adminId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header(tokenHeaderName, token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void f_listAdmin() throws Exception {
        mockMvc.perform(get("/admins/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header(tokenHeaderName, token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void g_saveBook() throws Exception {
        //TODO
    }

    @Test
    public void h_updateBook() throws Exception {
        //TODO
    }

    @Test
    public void i_removeBook() throws Exception {
        //TODO
    }


    @Test
    public void j_confirmBorrow() throws Exception {
        //TODO
    }

    @Test
    public void k_confirmReturn() throws Exception {
        //TODO
    }

}