package com.axellinoanggoro.binar_challenge6.view.adapter

import com.axellinoanggoro.binar_challenge6.room.DataFavMovie
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*

class FavoriteAdapterTest {

    @Mock
    private lateinit var mockListener: FavoriteAdapter.OnItemClickListener

    @Test
    fun getItemCount_withEmptyList_returnsZero() {
        val mockListener = mock(FavoriteAdapter.OnItemClickListener::class.java)
        val adapter = FavoriteAdapter(emptyList(), mockListener)
        val itemCount = adapter.itemCount
        assertEquals(0, itemCount)
    }

    @Test
    fun getItemCount_withNonEmptyList_returnsCorrectCount() {
        mockListener = mock(FavoriteAdapter.OnItemClickListener::class.java)
        val favorites = listOf(
            DataFavMovie(1, "title1", "31 Mei 2023", "lorem ipsum", "img1"),
            DataFavMovie(2, "title2", "1 Juni 2023", "lorem ipsum", "img2"),
            DataFavMovie(3, "title3", "2 Juni 2023", "lorem ipsum", "img3")
        )

        val adapter = FavoriteAdapter(favorites, mockListener)
        val itemCount = adapter.itemCount
        assertEquals(favorites.size, itemCount)
    }

}