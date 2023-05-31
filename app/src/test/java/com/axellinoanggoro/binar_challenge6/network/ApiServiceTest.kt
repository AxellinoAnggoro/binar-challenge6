import com.axellinoanggoro.binar_challenge6.network.ApiService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetPopularMovieSuccess() {
        val responseBody = "{ \"results\": [ { \"id\": 1, \"title\": \"Movie 1\" } ] }"
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(responseBody))

        val call = apiService.getPopularMovie()
        val response = call.execute()

        assert(response.isSuccessful)
        assert(response.body()?.results?.isNotEmpty() == true)
        assert(response.body()?.results?.get(0)?.id == 1)
        assert(response.body()?.results?.get(0)?.title == "Movie 1")
    }

    @Test
    fun testGetPopularMovieFailure() {
        mockWebServer.enqueue(MockResponse().setResponseCode(500))
        val call = apiService.getPopularMovie()
        val response = call.execute()

        assert(!response.isSuccessful)
    }
}