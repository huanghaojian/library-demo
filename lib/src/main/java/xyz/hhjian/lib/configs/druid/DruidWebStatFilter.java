package xyz.hhjian.lib.configs.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * <p>配置Druid的StatFilter</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclusions", value = "*.js, *.gif, *.jpg, *.bmp, *.png, *.css, *.ico, /druid/*")// 忽略资源
        })
public class DruidWebStatFilter extends WebStatFilter {
}
