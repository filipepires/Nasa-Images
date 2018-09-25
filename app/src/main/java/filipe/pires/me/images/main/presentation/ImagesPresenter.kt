package filipe.pires.me.images.main.presentation

import filipe.pires.me.images.main.domain.GetImageListUseCaseForSearchTerm
import filipe.pires.me.images.main.presentation.model.DataItem


class ImagesPresenter(
        private val view: ImagesContract.View,
        private val getImageListInteractor: GetImageListUseCaseForSearchTerm
) : ImagesContract.Actions {

    override suspend fun onCreate() {
        view.showLoading()
        val model = ImageModelMapper().convertToImageModel(
                getImageListInteractor.execute(searchTerm = "Nebula")
        )
        view.bindThumbnails(model.thumbnails, model.items)
        view.hideLoading()
    }

    override fun onImageClicked(item: DataItem) {
        view.routeToSingleItem(item)
    }
}