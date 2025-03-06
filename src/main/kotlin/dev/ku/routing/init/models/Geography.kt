package dev.ku.routing.init.models

import jakarta.persistence.Entity
import jakarta.persistence.GenerationType
import jakarta.persistence.*

@Entity
@Table(name = "geography")
data class Geography(
    @Id
    val id: String,
    val zipCode :  String,
    val province : String,
    val district : String,
    val lat : Double,
    val lng : Double,
){
    constructor() : this("", "", "", "", 0.0, 0.0)
}