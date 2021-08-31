package com.invoker.childmoviegateway.router;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.http.HttpHeaders.REFERER;

/**
 * 2021/8/31-10:06 上午
 *
 * @author <a href="mailto:javajason@163.com">Jason(LiuJianJun)</a>
 */
@Configuration
public class SchoolBusRouter {

    public static final String MISS_REFERER_HEADER = "https://missav.com/";

    public static final String MISS_SOURCE_URL = "https://vz-8bd03a41-60e.b-cdn.net";

    public static final String MISS_ROUTER_URL = "http://127.0.0.1:9494/miss";

    @Bean
    public RouteLocator missRouteLocator(RouteLocatorBuilder routeBuilder) {
        return routeBuilder.routes()
                           .route("missBus", r -> r.path("/miss/**")
                                                   .filters(f -> f.addRequestHeader(REFERER,
                                                                                    MISS_REFERER_HEADER)
                                                                  .rewritePath("/miss/(?<segment>.*)", "/${segment}"))
                                                   .uri(MISS_SOURCE_URL))
                           .build();
    }

}
