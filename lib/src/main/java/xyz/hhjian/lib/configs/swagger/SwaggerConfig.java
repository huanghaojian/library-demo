package xyz.hhjian.lib.configs.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import xyz.hhjian.lib.configs.security.TokenSetting;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Swagger rest api 文档 配置/p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private TokenSetting tokenSetting;
    @Value("${swagger.title}")
    private String title;
    @Value("${swagger.description}")
    private String description;
    @Value("${swagger.version}")
    private String version;
    @Value("${swagger.basePackage}")
    private String basePackage;
    @Value("${swagger.contact.author}")
    private String author;
    @Value("${swagger.contact.url}")
    private String url;
    @Value("${swagger.contact.email}")
    private String email;


    /**
     * 文档描述
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version(version)
                .contact(new Contact(author, url, email))
                .build();
    }

    /**
     * 配置全局方法参数注释
     */
    private List<Parameter> globalOperationParameters() {
        List<Parameter> parameters = new ArrayList<>();
        ParameterBuilder tokenParamBuilder = new ParameterBuilder();
        tokenParamBuilder.name(tokenSetting.getTokenHeaderName())
                .description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue(tokenSetting.getTokenHead())
                .required(true).build();
        parameters.add(tokenParamBuilder.build());
        return parameters;
    }

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .build().apiInfo(apiInfo())
                .globalOperationParameters(globalOperationParameters());
    }
}
