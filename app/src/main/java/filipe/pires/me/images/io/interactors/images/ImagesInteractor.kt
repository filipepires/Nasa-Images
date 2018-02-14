package filipe.pires.me.images.io.interactors.images

import android.support.annotation.VisibleForTesting
import filipe.pires.me.images.io.NasaImagesInterface
import filipe.pires.me.images.io.TransactionCallback
import filipe.pires.me.images.io.domainobjects.Info
import filipe.pires.me.images.io.domainobjects.Item
import filipe.pires.me.images.io.entities.DataItem
import filipe.pires.me.images.io.interactors.InteractorResponse
import filipe.pires.me.images.presentation.images.ImagesContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class ImagesInteractor(private val restClient: Retrofit) : ImagesContract.Requests {

    data class ImageListResponse(
            val thumbnails: List<String>,
            val items: List<DataItem>
    ) : InteractorResponse

    override fun getImageList(callback: TransactionCallback, searchTerm: String) {
        val nasaImagesService = restClient.create(NasaImagesInterface::class.java)
        val call = nasaImagesService.search(searchTerm)
        call.enqueue(object : Callback<Info> {
            override fun onResponse(call: Call<Info>, response: Response<Info>) {
                callback.onSuccess(extractResponse(response.body()?.collection?.items))
            }

            override fun onFailure(call: Call<Info>, t: Throwable) {
            }
        })
    }

    @VisibleForTesting
    fun extractResponse(items: List<Item>?): ImageListResponse {
        val thumbnailList = ArrayList<String>()
        val imageList = ArrayList<DataItem>()

        items?.map {
            thumbnailList.add(it.links[0].href)
            imageList.add(DataItem(
                    extractImageLink(it.links[0].href),
                    it.data[0].title,
                    it.data[0].description
            ))
        }

        return ImageListResponse(thumbnailList, imageList)
    }

    private fun extractImageLink(thumbnailUrl: String): String {
        val imageUrl = thumbnailUrl.split("~")
        return imageUrl[0] + "~orig.jpg"
    }
}