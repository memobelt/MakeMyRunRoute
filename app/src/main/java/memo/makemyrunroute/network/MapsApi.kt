package memo.makemyrunroute.network

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MapsApi {
    @GET("nearestRoads?")
    fun getPlaceId(@Query("points") coordString: String,
                   @Query("key") key: String): Flowable<NearestRoadsResponse>

    @GET("json?")
    fun getStreets(@Query("placeid") placeId: String,
                   @Query("key") key: String): Flowable<MapsResponse>
}