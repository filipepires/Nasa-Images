package filipe.pires.me.images.presentation.images

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import filipe.pires.me.images.io.TransactionCallback
import filipe.pires.me.images.io.entities.DataItem
import filipe.pires.me.images.io.interactors.images.ImagesInteractor
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.util.*

class ImagesPresenterTest {

    private val view = mock(ImagesContract.View::class.java)

    private val interactor = mock(ImagesContract.Requests::class.java)

    private val router = mock(ImagesContract.Routes::class.java)

    private val presenter = ImagesPresenter(view, interactor, router)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when receives a list of images, bind thumbnails`() {
        val thumbnails = Arrays.asList("1", "2", "3", "4")
        val dataItems = Arrays.asList(
                mock(DataItem::class.java),
                mock(DataItem::class.java),
                mock(DataItem::class.java),
                mock(DataItem::class.java)
        )
        givenResponseWithData(thumbnails, dataItems)
        presenter.onCreate()
        verify(view).bindThumbnails(thumbnails, dataItems)
    }

    private fun givenResponseWithData(thumbnails: MutableList<String>, dataItems: MutableList<DataItem>) {
        val response = ImagesInteractor.ImageListResponse(thumbnails, dataItems)
        doAnswer {
            val transaction = it.arguments[0] as TransactionCallback
            transaction.onSuccess(response)
        }.whenever(interactor).getImageList(any(), any())
    }

    @Test
    fun `when image is clicked, start single item activity`(){
        val item = mock(DataItem::class.java)
        presenter.onImageClicked(item)
        verify(router).startSingleItemActivity(item)
    }

}