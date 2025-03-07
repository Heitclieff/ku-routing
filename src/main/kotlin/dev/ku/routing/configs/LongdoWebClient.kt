package dev.ku.routing.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.Builder

@Configuration
class LongdoWebClient {

    @Bean
    fun longdoClient (builder: WebClient.Builder) : WebClient {
        return builder.baseUrl("https://api.longdo.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.geo+json;charset=UTF-8")
            .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.geo+json") // รองรับ GeoJSON
            .build()
    }

    @Bean
    fun searchLongdoClient (builder: Builder) : WebClient {
        return builder.baseUrl("https://search.longdo.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.geo+json;charset=UTF-8")
            .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.geo+json") // รองรับ GeoJSON
            .build()
    }
}