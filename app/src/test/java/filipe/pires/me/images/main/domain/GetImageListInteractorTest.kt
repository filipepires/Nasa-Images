package filipe.pires.me.images.main.domain


import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import filipe.pires.me.images.main.data.ImagesRepository
import filipe.pires.me.images.main.domain.entities.NasaItem
import org.junit.Assert.assertEquals
import org.junit.Test

class GetImageListInteractorTest {

    private val repository = mock<ImagesRepository>()
    private val interactor = GetImageListInteractor(repository)

    @Test
    fun `when receives a list of items, returns image list`() {
        val images = listOf(
                NasaItem(
                        id = "some id",
                        title = "some title",
                        description = "some description",
                        imageUrl = "some url",
                        thumbnailUrl = "some thumbnail"
                )
        )
        whenever(repository.getImageListForSearchTerm(any()))
                .thenReturn(images)
        assertEquals(images, interactor.execute("a search term"))
    }

    @Test
    fun `when receives an error, returns empty image list`() {
        whenever(repository.getImageListForSearchTerm(any()))
                .thenThrow(RepositoryException(RepositoryExceptionType.NETWORK, "a message"))
        assertEquals(emptyList<NasaItem>(), interactor.execute("a search term"))

    }
}