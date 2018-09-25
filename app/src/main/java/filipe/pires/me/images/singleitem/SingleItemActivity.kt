package filipe.pires.me.images.singleitem

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import filipe.pires.me.images.R
import filipe.pires.me.images.main.presentation.model.DataItem
import kotlinx.android.synthetic.main.activity_single_item.*


class SingleItemActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_LINK = "extra_link"

        fun callingIntent(context: Context, dataItem: DataItem): Intent {
            val intent = Intent(context, SingleItemActivity::class.java)
            intent.putExtra(EXTRA_LINK, dataItem)
            return intent
        }
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