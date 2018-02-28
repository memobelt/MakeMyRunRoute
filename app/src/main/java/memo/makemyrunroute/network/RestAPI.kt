package memo.makemyrunroute.network

import io.reactivex.Flowable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor



class RestAPI {
    private val roadsApi: MapsApi
    private val mapsApi: MapsApi

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor {
            val request = it.request().newBuilder().addHeader("key", "AIzaSyBBayyn4Nu4k9eW2RhpCLyexQYlgnZOiyY").build()
            it.proceed(request)
        }.addInterceptor(logging).build()
        mapsApi = getRetrofitClient("https://maps.googleapis.com/maps/api/place/details/", okHttpClient)
        roadsApi = getRetrofitClient("https://roads.googleapis.com/v1/", okHttpClient)
    }

    fun getPlaceId(coordinate: Point): Flowable<NearestRoadsResponse> {
        return roadsApi.getPlaceId(makeCoordsToString(coordinate), "AIzaSyCcnXK3JFjbN7wDs0p8wJ-L55oGp2k3ebo")
    }

    fun getStreets(placeId: String): Flowable<MapsResponse> {
        return mapsApi.getStreets(placeId, "AIzaSyAp_F-RMaS6Q6QxHoAy9ycwp5LaOCYw2W0")
    }

    fun makeCoordsToString(coordinate: Point): String {
        return coordinate.latitude.toString() + "," + coordinate.longitude.toString()
    }

    private fun getRetrofitClient(baseUrl: String, okHttpClient: OkHttpClient): MapsApi {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MapsApi::class.java)
    }
}