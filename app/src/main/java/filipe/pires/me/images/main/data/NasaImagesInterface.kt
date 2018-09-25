package filipe.pires.me.images.main.data

import filipe.pires.me.images.main.data.datatransferobject.Info
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NasaImagesInterface {

    @GET("search")
    fun search(
            @Query("q") name: String,
            @Query("media_type") media_type: String = "image"
    ): Call<Info>
}