package filipe.pires.me.images.main.domain

import filipe.pires.me.images.main.domain.entities.NasaItem


interface GetImageListUseCaseForSearchTerm {
    fun execute(searchTerm: String): List<NasaItem>
}