package filipe.pires.me.images.routes.images

import android.content.Context
import android.content.Intent
import filipe.pires.me.images.io.entities.DataItem
import filipe.pires.me.images.presentation.images.ImagesContract
import filipe.pires.me.images.presentation.singleitem.SingleItemActivity


class ImagesRouter(private val context: Context) : ImagesContract.Routes {
    override fun startSingleItemActivity(dataItem: DataItem) {
        val singleItemIntent = Intent(context, SingleItemActivity::class.java)
        singleItemIntent.apply {
            putExtra(SingleItemActivity.EXTRA_LINK, dataItem)
        }
        context.startActivity(singleItemIntent)
    }
}