package com.heeexy.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Lingling
 * @description 资源配置路径
 * @date 2019/7/2 0002
 */

@ComponentScan
@Configuration
public class MyFileConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //和页面有关的静态目录都放在项目的static目录下
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //上传的图片在E盘下的Image/rent目录下，访问路径如：http://localhost:8080/Image/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        //其中/Image/rent表示访问的前缀。"file:E:/Image/rent"是文件真实的存储路径
        registry.addResourceHandler("/image/**").addResourceLocations("file:C:\\image\\rent\\");
        super.addResourceHandlers(registry);
    }
}

