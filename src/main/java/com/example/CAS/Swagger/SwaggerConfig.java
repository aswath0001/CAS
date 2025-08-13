package com.example.CAS.Swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.*;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student Management API")
                        .version("1.0")
                        .description("API for managing student data with bulk upload functionality"))
                .addSecurityItem(new SecurityRequirement().addList("JWT"))
                .components(new Components()
                        .addSecuritySchemes("JWT", new SecurityScheme()
                                .name("JWT")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                        .addSchemas("MultipartFile", new Schema()
                                .type("string")
                                .format("binary")
                                .description("Excel (.xlsx) or TXT file containing student data")))
                .paths(new Paths()
                        .addPathItem("/Students/upload", new PathItem()
                                .post(new Operation()
                                        .summary("Upload students in bulk")
                                        .description("Upload Excel (.xlsx) or TXT file containing student data")
                                        .requestBody(new RequestBody()
                                                .content(new Content()
                                                        .addMediaType("multipart/form-data", new MediaType()
                                                                .schema(new Schema()
                                                                        .type("object")
                                                                        .addProperty("file", new Schema()
                                                                                .$ref("#/components/schemas/MultipartFile"))))))
                                        .addTagsItem("Student Management")
                                        .responses(new ApiResponses()
                                                .addApiResponse("200", new ApiResponse()
                                                        .description("Students uploaded successfully"))
                                                .addApiResponse("400", new ApiResponse()
                                                        .description("Invalid file format"))
                                                .addApiResponse("401", new ApiResponse()
                                                        .description("Unauthorized"))))));
    }
}