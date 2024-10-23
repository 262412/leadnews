package com.heima.app.getway.filter;

import com.heima.app.getway.utils.AppJwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizeFilter implements Ordered, GlobalFilter {
    /**
     * 自定义网关过滤器，用于处理进入应用的每个HTTP请求
     * 主要功能是验证请求中的JWT令牌，以确定是否允许请求继续
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取当前请求和响应对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 如果请求路径包含"/api/v1/login"，则不进行令牌验证，直接放行
        if (request.getURI().getPath().contains("/api/v1/login")) {
            return chain.filter(exchange);
        }

        // 从请求头中获取令牌
        String token = request.getHeaders().getFirst("token");

        // 如果令牌为空，则返回401未授权状态
        if(StringUtils.isEmpty(token)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        try {
            // 解析令牌主体
            Claims claimsBody = AppJwtUtil.getClaimsBody(token);
            // 验证令牌有效性，如果令牌无效，则返回401未授权状态
            int result = AppJwtUtil.verifyToken(claimsBody);
            if(result == 1 || result == 2){
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
        } catch (Exception e) {
            // 如果解析或验证令牌时发生异常，则返回401未授权状态
            e.printStackTrace();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        // 令牌验证通过，继续处理请求
        return chain.filter(exchange);
    }

    /**
     * 获取过滤器的顺序，数值越小，优先级越高
     * 这里返回0表示此过滤器优先级最高
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
