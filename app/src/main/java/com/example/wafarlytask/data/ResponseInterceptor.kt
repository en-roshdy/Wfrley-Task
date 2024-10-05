import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ResponseInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        val response: Response = chain.proceed(request)

        when (response.code) {
            200 -> {
                println("Response is successful!")
            }
            401 -> {

            }
            500 -> {
                // تعامل مع أخطاء السيرفر
                println("Server error - 500")
            }
            else -> {
                println("Other response code: ${response.code}")
            }
        }
        return response
    }
}
