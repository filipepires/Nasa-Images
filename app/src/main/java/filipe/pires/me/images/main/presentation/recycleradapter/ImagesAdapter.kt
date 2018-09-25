package filipe.pires.me.images.main.presentation.recycleradapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import filipe.pires.me.images.R
import kotlinx.android.synthetic.main.recycler_item_image.view.*


class ImagesAdapter(
        private val imagePath: List<String>,
        private val imageClickListeners: List<View.OnClickListener>
) : RecyclerView.Adapter<ImagesAdapter.ImageHolder>() {

    override fun getItemCount(): Int = imagePath.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder = ImageHolder(
            LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.recycler_item_image, parent, false))

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.itemView.setOnClickListener(imageClickListeners[position])
        val currentUri = Uri.parse(imagePath[position])
        Glide.with(holder.itemView.context)
                .load(currentUri)
                .apply(RequestOptions().placeholder(ColorDrawable(Color.LTGRAY)).centerCrop())
                .into(holder.itemView.image_preview)
    }

    class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}