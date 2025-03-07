package dev.ku.routing.direction.controllers

import dev.ku.routing.direction.models.DirectionAddressModel
import dev.ku.routing.direction.models.DirectionRequestModel
import dev.ku.routing.direction.models.DirectionSearchModel
import dev.ku.routing.direction.services.DirectionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.resource.ResourceUrlProvider
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/direction")
class DirectionController(
    private val direction: DirectionService,
    private val resourceUrlProvider: ResourceUrlProvider
) {
    @PostMapping("/routing")
    fun getDirection(@RequestBody request:DirectionRequestModel): Mono<*> {
        return direction.getDirectionByJson(request)
    }

    @PostMapping("/search")
    fun searchDirection(@RequestBody request:DirectionSearchModel) : Mono<*> {
        return direction.searchDirection(request)
    }

    @PostMapping("/addresses")
    fun getAddressDirection(@RequestBody request:DirectionAddressModel) : Any {
        return direction.getAddressByGeometry(request)
    }

}