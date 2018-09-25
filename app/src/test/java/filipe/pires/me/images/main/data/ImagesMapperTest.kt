package filipe.pires.me.images.main.data

import filipe.pires.me.images.main.data.datatransferobject.Datum
import filipe.pires.me.images.main.data.datatransferobject.Item
import filipe.pires.me.images.main.data.datatransferobject.ItemLink
import org.junit.Assert
import org.junit.Test

class ImagesMapperTest {

    private val mapper = ImagesMapper()

    @Test
    fun `when receiving a list of items, parse them to a image list response`() {
        val items = ArrayList<Item>()
        for (i in 0..2) {
            val item = Item()
            item.data = ArrayList<Datum>()
            item.links = ArrayList<ItemLink>()
            val dataElement = Datum()
            dataElement.title = "title $i"
            dataElement.description = "description $i"
            dataElement.nasaId = "id$i"
            item.data.add(dataElement)
            val link = ItemLink()
            link.href = "image$i~something.png"
            item.links.add(link)
            items.add(item)
        }
        val response = mapper.convertToNasaItemList(items)
        Assert.assertEquals(3, response.size)
        Assert.assertEquals("image0~orig.jpg", response[0].imageUrl)
    }
}