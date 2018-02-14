package filipe.pires.me.images.presentation.singleitem

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import filipe.pires.me.images.R
import filipe.pires.me.images.io.entities.DataItem
import kotlinx.android.synthetic.main.activity_single_item.*


class SingleItemActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LINK = "extra_link"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_item)
        val item = intent.getParcelableExtra<DataItem>(EXTRA_LINK)
        title = item.title
        Glide.with(applicationContext)
                .load(item.imageLink)
                .apply(RequestOptions().placeholder(ColorDrawable(Color.LTGRAY)).centerCrop())
                .into(image)
        description.text = item.description
    }
}