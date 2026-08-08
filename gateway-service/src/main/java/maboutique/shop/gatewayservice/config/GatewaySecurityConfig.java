//package maboutique.shop.gatewayservice.config;
//
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//
//@Configuration
//public class GatewaySecurityConfig {
//
//    @Bean
//    public GlobalFilter authorizationHeaderFilter() {
//        return (exchange, chain) -> {
//            HttpMethod method = exchange.getRequest().getMethod();
//            String path = exchange.getRequest().getURI().getPath();
//
//            // Laisser passer les requêtes OPTIONS (préflight CORS)
//            if (HttpMethod.OPTIONS.equals(method)) {
//                return chain.filter(exchange); // laisser passer vers CorsWebFilter
//            }
//
//
//            // On laisse passer les endpoints publics (authentification, etc.)
//            if (path.startsWith("/api/auth")
//                    || (HttpMethod.GET.equals(method) && path.startsWith("/api/categories"))
//                    || (HttpMethod.GET.equals(method) && path.startsWith("/api/produits"))) {
//                return chain.filter(exchange);
//            }
//
//            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
//
//            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                return exchange.getResponse().setComplete();
//            }
//
//            // Si le header est présent, on continue vers le microservice
//            return chain.filter(exchange);
//        };
//    }
//}
//
