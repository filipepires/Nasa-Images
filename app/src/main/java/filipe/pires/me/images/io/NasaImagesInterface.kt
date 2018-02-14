package filipe.pires.me.images.io

import filipe.pires.me.images.io.domainobjects.Info
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