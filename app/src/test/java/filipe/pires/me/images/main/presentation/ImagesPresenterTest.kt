package filipe.pires.me.images.main.presentation

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import filipe.pires.me.images.main.domain.GetImageListUseCaseForSearchTerm
import filipe.pires.me.images.main.domain.entities.NasaItem
import filipe.pires.me.images.main.presentation.model.DataItem
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import java.util.*

class ImagesPresenterTest {

    private val view = mock(ImagesContract.View::class.java)
    private val interactor = mock(GetImageListUseCaseForSearchTerm::class.java)
    private val presenter = ImagesPresenter(view, interactor)

    @Test
    fun `when receives a list of images, bind thumbnails`() {
        val item = NasaItem(
                id = "some id",
                title = "some title",
                description = "some description",
                imageUrl = "some url",
                thumbnailUrl = "some thumbnail"
        )
        val dataItems = Arrays.asList(
                item,
                item,
                item
        )
        runBlocking {
            whenever(interactor.execute(any())).thenAnswer { dataItems }
            presenter.onCreate()
            verify(view).bindThumbnails(any(), any())
        }
    }

    @Test
    fun `when image is clicked, start single item activity`() {
        val item = mock(DataItem::class.java)
        presenter.onImageClicked(item)
        verify(view).routeToSingleItem(item)
    }
}