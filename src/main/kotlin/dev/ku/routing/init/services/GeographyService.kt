package dev.ku.routing.init.services

import dev.ku.routing.init.models.Geography
import dev.ku.routing.init.repository.GeographyRepository
import dev.ku.routing.models.PaginationModel
import dev.ku.routing.models.ResponseModel
import org.springframework.stereotype.Service
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

@Service
class GeographyService(
    private val geographyRepository: GeographyRepository
) {
    fun getGeographyById(id: String): ResponseModel<Geography> {
        return runCatching {
            val result = geographyRepository.findById(id).orElse(null)
            ResponseModel(
                status = 200,
                message = if (result == null) "ไม่พบข้อมูล" else "ดึงข้อมูลสำเร็จ",
                totalElements = if (result == null) 0 else 1,
                content = result
            )
        }.getOrElse {
            ResponseModel(
                status = 500,
                message = "ดึงข้อมูลไม่สำเร็จ",
                totalElements = 0
            )
        }
    }

    fun searchGeography(page: Int, size: Int, sortDirection: String): PaginationModel<List<Geography>> {
        val direction =
            if (sortDirection == "asc")
                Sort.by(Sort.Order.asc("id"))
            else
                Sort.by(Sort.Order.desc("id"));

        val pageable: Pageable = PageRequest.of(page - 1, size, direction)

        return runCatching {
            val result = geographyRepository.findAll(pageable)
            PaginationModel(
                status = 200,
                message = if (result.content.size > 0 ) "ดึงข้อมูลสำเร็จ" else "ไม่พบข้อมูล",
                page = page,
                size = size,
                totalElements = result.totalElements,
                content = result.content
            )
        }.getOrElse {
            PaginationModel(
                status = 500,
                message = "ดึงข้อมูลไม่สำเร็จ",
                page = page,
                size = size,
                totalElements = 0,
                content = emptyList()
            )
        }
    }
}