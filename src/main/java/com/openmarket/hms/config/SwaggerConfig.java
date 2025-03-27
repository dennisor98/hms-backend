package com.openmarket.hms.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
    @Value("${spring.profiles.active}")
    private String profileActive;
    
    private final PasswordEncoder passwordEncoder;

    public SwaggerConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
     OpenAPI customOpenAPI() {
        Server localServer = new Server()
            .url("http://localhost:8080/open-market-hms")
            .description("Localhost Server URL");
        
        Contact contact = new Contact()
            .email("open.market@gmail.com")
            .name("Open Market HMS");
        
        Info info = new Info()
            .contact(contact)
            .description("Open Market HMS API(s)")
            .title("Open Market HMS")
            .version("2.0.0")
            .license(new License()
                .name("Apache 2.0")
                .url("http://springdoc.org"));
        
        // Define custom header here
        Components components = new Components();
        components.addHeaders("X-Custom-Header", 
            new Header()
                .description("Description of custom header")
                .schema(new StringSchema()));
        
        components.addSecuritySchemes("Bearer Authentication", createAPIKeyScheme());
        
        return new OpenAPI()
            .info(info)
            .addServersItem(localServer)
            .components(components)
            .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"));
    }
    
    
   
    
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer");
    }
}