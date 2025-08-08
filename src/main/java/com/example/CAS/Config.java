package com.example.CAS;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
   @Bean
   public OpenAPI customOpenApi (){
       return new OpenAPI().info(new Info().title("Student Course API").version("1.0")
               .description("API Documentation with authentication "))
               .addSecurityItem(new SecurityRequirement().addList("Basic auth"))
               .components(new io.swagger.v3.oas.models.Components()
                       .addSecuritySchemes("Basic auth",new SecurityScheme()
                       .type(SecurityScheme.Type.HTTP)
                       .scheme("basic")));
   }
}
