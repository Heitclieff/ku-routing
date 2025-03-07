package dev.ku.routing.direction.interfaces
import dev.ku.routing.direction.models.DirectionAddressModel
import dev.ku.routing.direction.models.DirectionRequestModel
import dev.ku.routing.direction.models.DirectionSearchModel
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface DirectionInterface {
    fun getDirectionByJson(json:DirectionRequestModel): Mono<*>
    fun searchDirection(request:DirectionSearchModel): Mono<*>
    fun getAddressByGeometry(request: DirectionAddressModel): Any
}
