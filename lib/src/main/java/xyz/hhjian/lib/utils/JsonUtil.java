package xyz.hhjian.lib.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Json和Bean转换工具类</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.21
 */
public class JsonUtil {

    private JsonUtil() {
    }

    public static String toJson(Object object) {
        Writer writer = new StringWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(writer, object);
            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toBean(String json, Class<T> beanClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        T bean = null;
        try {
            bean = objectMapper.readValue(json, beanClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static Map toMap(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap map = null;
        try {
            map = objectMapper.readValue(json, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
