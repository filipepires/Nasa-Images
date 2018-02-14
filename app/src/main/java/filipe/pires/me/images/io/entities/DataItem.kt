package filipe.pires.me.images.io.entities

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItem(
        val imageLink: String,
        val title: String,
        val description: String
) : Parcelable