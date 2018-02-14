package filipe.pires.me.images.presentation.images

import filipe.pires.me.images.io.TransactionCallback
import filipe.pires.me.images.io.entities.DataItem
import filipe.pires.me.images.io.interactors.InteractorResponse
import filipe.pires.me.images.io.interactors.images.ImagesInteractor


class ImagesPresenter(
        private val view: ImagesContract.View,
        private val interactor: ImagesContract.Requests,
        private val router: ImagesContract.Routes
) : ImagesContract.Actions {

    override fun onCreate() {
        val callback = object : TransactionCallback {
            override fun onSuccess(response: InteractorResponse) {
                response as ImagesInteractor.ImageListResponse
                view.bindThumbnails(response.thumbnails, response.items)
            }
        }
        interactor.getImageList(callback, "Nebula")
    }

    override fun onImageClicked(dataItem: DataItem) {
        router.startSingleItemActivity(dataItem)
    }
}