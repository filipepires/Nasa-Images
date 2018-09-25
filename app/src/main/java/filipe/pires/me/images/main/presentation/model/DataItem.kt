package filipe.pires.me.images.main.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItem(
        val imageLink: String,
        val title: String,
        val description: String
) : Parcelable