package filipe.pires.me.images.main.data

import filipe.pires.me.images.main.data.datatransferobject.Item
import filipe.pires.me.images.main.domain.entities.NasaItem


class ImagesMapper {
    fun convertToNasaItemList(items: List<Item>): List<NasaItem> =
            items.map {
                NasaItem(
                        it.data[0].nasaId,
                        it.data[0].title,
                        it.data[0].description,
                        extractImageLink(it.links[0].href),
                        it.links[0].href
                )
            }

    private fun extractImageLink(thumbnailUrl: String): String {
        val imageUrl = thumbnailUrl.split("~")
        return imageUrl[0] + "~orig.jpg"
    }
}