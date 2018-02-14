package filipe.pires.me.images.presentation.images

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import filipe.pires.me.images.R
import filipe.pires.me.images.io.entities.DataItem
import filipe.pires.me.images.io.interactors.images.ImagesInteractor
import filipe.pires.me.images.presentation.images.recycleradapter.ImagesAdapter
import filipe.pires.me.images.presentation.images.recycleradapter.TransparentDecorator
import filipe.pires.me.images.routes.images.ImagesRouter
import kotlinx.android.synthetic.main.activity_main.*
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
                ImagesInteractor(restClient),
                ImagesRouter(applicationContext)
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
        presenter.onCreate()
    }

    override fun bindThumbnails(thumbnails: List<String>, imageLinks: List<DataItem>) {
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