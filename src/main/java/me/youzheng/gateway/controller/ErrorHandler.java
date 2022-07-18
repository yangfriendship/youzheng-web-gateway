package me.youzheng.gateway.controller;

import java.nio.charset.StandardCharsets;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ErrorHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        final String errorMessage = "{\"message\" :\"404~\"}";

        DataBuffer buffer = exchange.getResponse().bufferFactory()
            .wrap(errorMessage.getBytes(StandardCharsets.UTF_8));
        return exchange.getResponse().writeWith(Flux.just(buffer));
    }

}