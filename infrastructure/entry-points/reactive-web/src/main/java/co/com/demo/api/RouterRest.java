package co.com.demo.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class RouterRest {
@Bean
public RouterFunction<ServerResponse> routerFunction(Handler handler) {
    return route(GET("/api/usecase/"), handler::listenGETAllUseCase)
            .andRoute(GET("/api/usecase/{id}"), handler::listenGETUseCase)
            .andRoute(POST("/api/usecase/"), handler::listenPOSTUseCase)
            .andRoute(DELETE("/api/usecase/{id}"), handler::listenDELETEUseCase);

    }
}
