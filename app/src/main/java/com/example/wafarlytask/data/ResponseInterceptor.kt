import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

// إنشاء Interceptor
class ResponseInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        // تنفيذ الطلب
        val response: Response = chain.proceed(request)

        // التعامل مع الـ Response هنا
        when (response.code) {
            200 -> {
                // التعامل مع الـ response الناجح (OK)
                println("Response is successful!")
            }
            401 -> {
                // تعامل مع حالة عدم الترخيص (Unauthorized)
                println("Unauthorized - 401")
                // ممكن تـ handle إعادة التوجيه إلى صفحة تسجيل الدخول أو أي action آخر
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
