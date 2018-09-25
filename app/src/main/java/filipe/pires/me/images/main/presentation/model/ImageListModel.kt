package filipe.pires.me.images.main.presentation.model


data class ImageListModel(
        val thumbnails: List<String>,
        val items: List<DataItem>)