package org.swagger.lab2_swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server server = new Server();
        server.setDescription("Main server");

        Info info = new Info()
                .title("Tour service API")
                .version("1.0")
                .description("This API exposes main endpoints for tour service");

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
