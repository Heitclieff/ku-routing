package dev.ku.routing.init.controller

import dev.ku.routing.init.models.Geography
import dev.ku.routing.init.services.GeographyService
import dev.ku.routing.models.PaginationModel
import dev.ku.routing.models.ResponseModel
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/geography")
class GeographyController(
    private val geographyService: GeographyService
) {

    @GetMapping("/{id}")
    fun getGeography(@PathVariable id: String): ResponseModel<Geography> {
        return geographyService.getGeographyById(id)
    }

    @GetMapping("/search")
    fun searchGeography(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "5") size: Int,
        @RequestParam(defaultValue = "desc" ) sortDirection: String
    ): PaginationModel<List<Geography>> {
        return geographyService.searchGeography(page, size, sortDirection)
    }
}
