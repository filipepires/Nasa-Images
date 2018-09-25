package filipe.pires.me.images.main.data.datatransferobject

import filipe.pires.me.images.main.presentation.model.DataItem


data class ImageList(
        val thumbnails: List<String>,
        val items: List<DataItem>
)