package filipe.pires.me.images.main.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import filipe.pires.me.images.main.data.datatransferobject.*
import filipe.pires.me.images.main.data.datatransferobject.Collection
import filipe.pires.me.images.main.domain.RepositoryException
import filipe.pires.me.images.main.domain.RepositoryExceptionType
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit

class ImagesNetworkStorageTest {

    private val apiClient = mock<Retrofit>()
    private val storage = ImagesNetworkStorage(apiClient)

    @Test
    fun `given an item collection, return a list of nasa items`() {
        val result = mock<Response<Info>>()
        val body = mock<Info>()
        val collection = Collection().apply {
            items = listOf(
                    Item().apply {
                        data = listOf(
                                Datum().apply {
                                    nasaId = "some id"
                                    title = "some title"
                                    description = "some description"
                                    links = listOf(
                                            ItemLink().apply {
                                                href = "some image"
                                            }
                                    )
                                }
                        )
                    }
            )
        }
        whenever(result.isSuccessful).thenReturn(true)
        whenever(result.body()).thenReturn(body)
        whenever(body.collection).thenReturn(collection)
        assertEquals(1, storage.handleSearchResult(result).size)
    }

    @Test
    fun `given an empty list, throw exception`() {
        val result = mock<Response<Info>>()
        whenever(result.isSuccessful).thenReturn(true)
        whenever(result.message()).thenReturn("a message")
        try {
            storage.handleSearchResult(result).size
        } catch (exception: RepositoryException) {
            assertEquals(RepositoryExceptionType.NETWORK, exception.type)
            assertEquals("a message", exception.message)
        }
    }

    @Test
    fun `when request fails, throw exception`() {
        val result = mock<Response<Info>>()
        val body = mock<Info>()
        val collection = Collection().apply {
            items = listOf(
                    Item().apply {
                        data = listOf(
                                Datum().apply {
                                    nasaId = "some id"
                                    title = "some title"
                                    description = "some description"
                                    links = listOf(
                                            ItemLink().apply {
                                                href = "some image"
                                            }
                                    )
                                }
                        )
                    }
            )
        }
        whenever(result.isSuccessful).thenReturn(false)
        whenever(result.body()).thenReturn(body)
        whenever(body.collection).thenReturn(collection)
        whenever(result.message()).thenReturn("a message")
        try {
            storage.handleSearchResult(result).size
        } catch (exception: RepositoryException) {
            assertEquals(RepositoryExceptionType.NETWORK, exception.type)
            assertEquals("a message", exception.message)
        }
    }
}