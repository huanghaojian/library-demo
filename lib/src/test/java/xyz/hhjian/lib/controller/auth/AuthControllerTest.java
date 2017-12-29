package xyz.hhjian.lib.controller.auth;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import xyz.hhjian.lib.entity.bo.RegisterBO;
import xyz.hhjian.lib.utils.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <p>测试授权控制器</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder()
@Ignore
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static String username = String.valueOf(System.currentTimeMillis());
    private static String password = "123456";

    @Test
    public void register() throws Exception {
        RegisterBO registerForm = new RegisterBO();
        registerForm.setUsername(username);
        registerForm.setNickname("黃豪健");
        registerForm.setPassword(password);
        // 设置请求
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JsonUtil.toJson(registerForm));
        // 请求结果
        ResultActions resultActions = mockMvc.perform(builder);
        resultActions.andDo(MockMvcResultHandlers.print());
        // 是否成功
        resultActions.andExpect(status().isOk());
    }

//    @Test
//    public void login() throws Exception {
//        AuthForm authForm = new AuthForm();
//        authForm.setUsername(username);
//        authForm.setPassword(password);
//        MvcResult mvcResult = mockMvc.perform(
//                post("/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(JsonUtil.toJson(authForm)))
//                .andDo(print())
//                .andExpect(content().json("{'errcode':0}"))
//                .andReturn();
//
//        String response = mvcResult.getResponse().getContentAsString();
//        Map map = JsonUtil.toMap(response);
//        Map data = (Map) map.get("data");
//        System.out.println("userId:" + data.get("userId"));
//        System.out.println("token:" + data.get("token"));
//    }

}
