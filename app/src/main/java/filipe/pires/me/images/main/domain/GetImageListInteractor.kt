package filipe.pires.me.images.main.domain

import filipe.pires.me.images.main.data.ImagesRepository
import filipe.pires.me.images.main.domain.entities.NasaItem


class GetImageListInteractor(
        private val repository: ImagesRepository
) : GetImageListUseCaseForSearchTerm {

    @Throws(RepositoryException::class)
    override fun execute(searchTerm: String): List<NasaItem> {
        return try {
            repository.getImageListForSearchTerm(searchTerm)
        } catch (exception: RepositoryException) {
            emptyList()
        }
    }
}