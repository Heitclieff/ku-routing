package dev.ku.routing.direction.models
import java.util.*

data class DirectionModel(
    val name: String,
    val type: String
)

data class DirectionRequestModel(
    val mode: String,
    val type : Number,
    val locale: String,
    val locations: List<Location>,
)

data class DirectionSearchModel(
    val keywords : String?=null,
    val lat: Double?=0.0,
    val lon: Double?=0.0,
    val locale: String
)

data class DirectionAddressModel(
    val lat: Double,
    val lon: Double,
    val locale : String
)

data class Location(
    val lon: Double,
    val lat: Double,
    val type: String
)

data class GeoJsonResponseModel(
    val type: String,
    val features: List<Feature>
)


data class Feature(
    val type: String,
    val geometry: GeometryModel,
    val properties: Map<String, Any>
)

data class GeometryModel(
    val type: String,
    val coordinates: List<List<Double>>
)

data class DirectionResponseModel(
    val code: Int,
    val type : String,
    val message: String,
    val content: List<Feature>
)

data class DirectionSearchResponseModel(
    val code: Int,
    val message: String,
    val content: List<DirectionSearchContent>
)


data class SearchModel(
    val meta: MetaModel,
    val data : List<DirectionSearchContent>,
)

data class MetaModel(
    val hasmore: Boolean,
    val start: Int,
    val end : Int,
    val keyword : String
)

data class DirectionSearchContent(
    val type : String,
    val id : String,
    val name : String,
    val lat : Double,
    val lon :Double,
    val icon : String,
    val url : String,
    val address : String,
    val tel : String,
    val contributor : String,
    val verified : Boolean,
    val obsoleted : Boolean,
    val custom_id : String?=null,
    val custom_detail : String?=null,
    val tag : List<String> ? =null,
    val working_hours : WorkingHour ?=null
)

data class WorkingHour(
    val remark : String,
    val hours : List<Any>
)

data class AddressModel(
    val geocode : String,
    val country : String,
    val province : String,
    val district : String,
    val subDistrict : String?=null,
    val postalCode : String?=null,
    val evaluation : Int,
    val road : String,
    val road_lon : Double,
    val road_lat : Double
)

data class AddressResponseModel(
    val code: Int,
    val message: String,
    val content: List<AddressModel>
)