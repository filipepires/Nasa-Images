package filipe.pires.me.images.main.presentation

import filipe.pires.me.images.main.domain.entities.NasaItem
import org.junit.Assert.assertEquals
import org.junit.Test

class ModelMapperTest {

    private val mapper = ImageModelMapper()

    @Test
    fun something() {
        val items = listOf(
                NasaItem(
                        id = "some id",
                        title = "some title",
                        description = "some description",
                        imageUrl = "some url",
                        thumbnailUrl = "some thumbnail"
                ))
        val result = mapper.convertToImageModel(items)
        assertEquals("some thumbnail", result.thumbnails[0])
        assertEquals("some title", result.items[0].title)
        assertEquals("some url", result.items[0].imageLink)
        assertEquals("some description", result.items[0].description)
    }
}