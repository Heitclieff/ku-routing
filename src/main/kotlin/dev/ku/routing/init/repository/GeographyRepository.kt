package dev.ku.routing.init.repository


import dev.ku.routing.init.models.Geography
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@Repository
interface GeographyRepository : JpaRepository<Geography, String> {
    override fun findAll(pageable: Pageable): Page<Geography>
}
