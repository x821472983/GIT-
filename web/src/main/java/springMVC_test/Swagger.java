package springMVC_test;


import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*自定义对swagger的配置*/
@Configuration
@EnableSwagger
public class Swagger {
    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementaion() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apilInfo()).includePatterns(".*?");
    }

    private ApiInfo apilInfo() {
        ApiInfo apiInfo = new ApiInfo("API接口测试平台", // 页面的标题
                "提供后台所有Restful接口", "My Apps API terms of com.dc.common.service",
                "My Apps API Contact Email",
                "My Apps API Licence Type", "My Apps API License URL");
        return apiInfo;
    }
}


