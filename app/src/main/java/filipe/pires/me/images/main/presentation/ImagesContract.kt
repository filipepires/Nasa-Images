package filipe.pires.me.images.main.presentation

import filipe.pires.me.images.main.presentation.model.DataItem


interface ImagesContract {

    interface View {
        fun bindThumbnails(thumbnails: List<String>, imageLinks: List<DataItem>)
        fun routeToSingleItem(item: DataItem)
        fun showLoading()
        fun hideLoading()
    }

    interface Actions {
        suspend fun onCreate()
        fun onImageClicked(item: DataItem)
    }
}