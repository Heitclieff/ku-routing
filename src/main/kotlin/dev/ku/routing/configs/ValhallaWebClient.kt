package dev.ku.routing.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class ValhallaWebClient {

    @Bean
    fun valhallaClient (builder:WebClient.Builder) : WebClient {
            return builder.baseUrl("https://valhalla1.openstreetmap.de")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
    }
}