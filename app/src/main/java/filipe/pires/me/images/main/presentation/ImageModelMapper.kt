package filipe.pires.me.images.main.presentation

import filipe.pires.me.images.main.domain.entities.NasaItem
import filipe.pires.me.images.main.presentation.model.DataItem
import filipe.pires.me.images.main.presentation.model.ImageListModel

class ImageModelMapper {

    fun convertToImageModel(items: List<NasaItem>): ImageListModel {
        val thumbnailList = ArrayList<String>()
        val imageList = ArrayList<DataItem>()

        items.map {
            thumbnailList.add(it.thumbnailUrl)
            imageList.add(DataItem(
                    it.imageUrl,
                    it.title,
                    it.description
            ))
        }

        return ImageListModel(thumbnailList, imageList)
    }
}
