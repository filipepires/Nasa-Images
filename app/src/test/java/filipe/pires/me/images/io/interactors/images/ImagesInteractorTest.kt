package filipe.pires.me.images.io.interactors.images

import filipe.pires.me.images.io.domainobjects.Datum
import filipe.pires.me.images.io.domainobjects.Item
import filipe.pires.me.images.io.domainobjects.ItemLink
import filipe.pires.me.images.io.domainobjects.Link
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import java.util.*
import kotlin.collections.ArrayList


class ImagesInteractorTest {

    private val restClient = mock(Retrofit::class.java)

    private val interactor = ImagesInteractor(restClient)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when receiving a list of items, parse them to a image list response`() {
        val items = ArrayList<Item>()
        for (i in 0..2) {
            val item = Item()
            item.data = ArrayList<Datum>()
            item.links = ArrayList<ItemLink>()
            val dataElement = Datum()
            dataElement.title = "title " + i
            dataElement.description = "description " + i
            item.data.add(dataElement)
            val link = ItemLink()
            link.href = "image$i~something.png"
            item.links.add(link)
            items.add(item)
        }
        val response = interactor.extractResponse(items)
        assertEquals(3, response.items.size)
        assertEquals(3, response.thumbnails.size)
        assertEquals("image0~orig.jpg", response.items[0].imageLink)
    }
}