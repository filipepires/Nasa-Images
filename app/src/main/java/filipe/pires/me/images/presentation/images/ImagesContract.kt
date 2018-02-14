package filipe.pires.me.images.presentation.images

import filipe.pires.me.images.io.TransactionCallback
import filipe.pires.me.images.io.entities.DataItem


interface ImagesContract {

    interface View {
        fun bindThumbnails(thumbnails: List<String>, imageLinks: List<DataItem>)
    }

    interface Actions {
        fun onCreate()
        fun onImageClicked(dataItem: DataItem)
    }

    interface Requests {
        fun getImageList(callback: TransactionCallback, searchTerm: String)
    }

    interface Routes {
        fun startSingleItemActivity(dataItem: DataItem)
    }
}