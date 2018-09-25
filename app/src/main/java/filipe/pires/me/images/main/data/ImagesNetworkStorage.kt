package filipe.pires.me.images.main.data

import android.support.annotation.VisibleForTesting
import filipe.pires.me.images.main.data.datatransferobject.Info
import filipe.pires.me.images.main.domain.RepositoryException
import filipe.pires.me.images.main.domain.RepositoryExceptionType
import filipe.pires.me.images.main.domain.entities.NasaItem
import retrofit2.Response
import retrofit2.Retrofit


class ImagesNetworkStorage(private val apiClient: Retrofit) : ImagesRepository {
    override fun getImageListForSearchTerm(searchTerm: String): List<NasaItem> {
        val nasaImagesService = apiClient.create(NasaImagesInterface::class.java)
        val result = nasaImagesService.search(searchTerm).execute()
        return handleSearchResult(result)
    }

    @VisibleForTesting
    fun handleSearchResult(result: Response<Info>): List<NasaItem> {
        if (result.isSuccessful)
            result.body()?.collection?.items?.let { return ImagesMapper().convertToNasaItemList(it) }
                    ?: throw RepositoryException(RepositoryExceptionType.NETWORK, result.message())
        else
            throw RepositoryException(RepositoryExceptionType.NETWORK, result.message())
    }
}