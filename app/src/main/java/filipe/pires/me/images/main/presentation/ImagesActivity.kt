package filipe.pires.me.images.main.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import filipe.pires.me.images.R
import filipe.pires.me.images.main.data.ImagesNetworkStorage
import filipe.pires.me.images.main.domain.GetImageListInteractor
import filipe.pires.me.images.main.presentation.model.DataItem
import filipe.pires.me.images.main.presentation.recycleradapter.ImagesAdapter
import filipe.pires.me.images.main.presentation.recycleradapter.TransparentDecorator
import filipe.pires.me.images.singleitem.SingleItemActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImagesActivity : AppCompatActivity(), ImagesContract.View {

    companion object {
        private const val BASE_URL = "https://images-api.nasa.gov/"
    }

    private val restClient: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    private val presenter: ImagesContract.Actions by lazy {
        ImagesPresenter(
                this,
                GetImageListInteractor(ImagesNetworkStorage(restClient))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        image_list.layoutManager = GridLayoutManager(
                applicationContext,
                resources.getInteger(R.integer.grid_size)
        )
        image_list.addItemDecoration(TransparentDecorator(
                resources.getDimensionPixelSize(R.dimen.small_margin),
                resources.getInteger(R.integer.grid_size)
        ))
        launch {
            presenter.onCreate()
        }
    }

    override fun bindThumbnails(thumbnails: List<String>, imageLinks: List<DataItem>) {
        launch(UI) {
            val onItemClickListeners = ArrayList<View.OnClickListener>()
            imageLinks.forEach { item ->
                onItemClickListeners.add(
                        View.OnClickListener {
                            presenter.onImageClicked(item)
                        })
            }
            image_list.adapter = ImagesAdapter(thumbnails, onItemClickListeners)
        }
    }

    override fun routeToSingleItem(item: DataItem) {
        startActivity(SingleItemActivity.callingIntent(applicationContext, item))
    }

    override fun showLoading() {
        launch(UI) {
            loading.visibility = VISIBLE
        }
    }

    override fun hideLoading() {
        launch(UI) {
            loading.visibility = GONE
        }
    }
}