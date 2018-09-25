package filipe.pires.me.images.main.data

import filipe.pires.me.images.main.domain.RepositoryException
import filipe.pires.me.images.main.domain.entities.NasaItem


interface ImagesRepository {
    @Throws(RepositoryException::class)
    fun getImageListForSearchTerm(searchTerm: String): List<NasaItem>
}