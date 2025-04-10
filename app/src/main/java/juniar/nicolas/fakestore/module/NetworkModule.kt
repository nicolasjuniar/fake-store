package juniar.nicolas.fakestore.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import juniar.nicolas.fakestore.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create()
    }

    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        OkHttpClient.Builder().apply {
            addInterceptor(
                httpLoggingInterceptor.apply {
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get<OkHttpClient>())
            .build()
    }
}