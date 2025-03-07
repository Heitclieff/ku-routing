package dev.ku.routing.configs.interfaces

import dev.ku.routing.direction.models.DirectionRequestModel
import reactor.core.publisher.Mono

interface ValhallaClientInterface {
    fun getDirectionByJson(json:DirectionRequestModel): Mono<*>
}