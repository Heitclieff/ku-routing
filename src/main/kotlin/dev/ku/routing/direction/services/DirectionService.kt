package dev.ku.routing.direction.services

import com.fasterxml.jackson.databind.ObjectMapper
import dev.ku.routing.configs.interfaces.ValhallaClientInterface
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import com.google.gson.Gson
import dev.ku.routing.direction.interfaces.DirectionInterface
import dev.ku.routing.direction.models.*
import io.github.cdimascio.dotenv.dotenv
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address
import org.springframework.http.MediaType
import reactor.core.publisher.Flux

@Service
class DirectionService(
   private val longdoClient: WebClient,
    private val searchLongdoClient: WebClient,
) : DirectionInterface {

    private val dotenv = dotenv()
    private val apiKey = dotenv["LONGDO_API_KEY"] ?: throw IllegalStateException("API_KEY not found in .env file")

    override fun getDirectionByJson(json: DirectionRequestModel) : Mono<*> {

        return longdoClient.get().uri { uriBuilder ->
            uriBuilder.path("/RouteService/geojson/route")
                .queryParam("key", apiKey)
                .queryParam("flat", json.locations[0].lat)
                .queryParam("flon", json.locations[0].lon)
                .queryParam("tlat", json.locations[1].lat)
                .queryParam("tlon", json.locations[1].lon)
                .queryParam("mode", json.mode)
                .queryParam("restrict", 0)
                .queryParam("locale", json.locale)
                .build()
        }
            .header("api-key", apiKey)
            .retrieve()
            .bodyToMono(GeoJsonResponseModel::class.java)
            .map { geojson ->
                DirectionResponseModel(
                    code = 200,
                    message = "ดึงข้อมูลสำเร็จ",
                    type = geojson.type,
                    content = geojson.features
                )
            }
    }

    override fun searchDirection(request: DirectionSearchModel): Mono<*> {
        return searchLongdoClient.get().uri { uriBuilder ->
            uriBuilder.path("/mapsearch/json/search")
                .queryParam("key", apiKey)
                .queryParam("locale", request.locale)
                request.keywords.let { uriBuilder.queryParam("keyword", it) }
                request.lat.let { uriBuilder.queryParam("lat", it) }
                request.lon.let { uriBuilder.queryParam("lon", it) }
                .build()
        }
            .header("api-key", apiKey)
            .retrieve()
            .bodyToMono(SearchModel::class.java)
            .map { model ->
                DirectionSearchResponseModel(
                    code = 200,
                    message = "ดึงข้อมูลสำเร็จ",
                    content = model.data
                )
            }
    }

    override fun getAddressByGeometry(request:DirectionAddressModel) : Any {


        return longdoClient.get().uri { uriBuilder ->
            uriBuilder.path("/map/services/addresses")
                .queryParam("key", apiKey)
                .queryParam("locale", request.locale)
                .queryParam("lat[]", request.lat)
                .queryParam("lon[]", request.lon)
                .build()
        }
            .header("api-key", apiKey)
            .retrieve()
            .bodyToMono(Array<AddressModel>::class.java)
            .map { array ->
                AddressResponseModel(
                    code = 200,
                    message = "ดึงข้อมูลสำเร็จ",
                    content = array.toList()
                )
            }
    }
}