package india.covid19.tracker.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import india.covid19.tracker.BuildConfig
import india.covid19.tracker.IntegerTypeAdapter
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author shinilms
 */

@Module
class NetModule {
    @Provides
    @Named("Cloud")
    @Singleton
    fun provideBaseRetrofit(@Named("Cloud") okHttpClient: OkHttpClient, cloudUrl: HttpUrl) =
        Retrofit.Builder()
            .baseUrl(cloudUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .client(okHttpClient)
            .build()

    //TODO use di
    fun provideGson(): Gson {
        return GsonBuilder().registerTypeAdapter(Int::class.java, IntegerTypeAdapter()).create()
    }

    @Provides
    @Named("Cloud")
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Provides
    @Named("Cloud")
    fun providesOkHttpClient(
        @Named("Cloud") okHttpClientBuilder: OkHttpClient.Builder
    ): OkHttpClient {
        return okHttpClientBuilder.build()
    }

    @Provides
    fun provideCloudUrl(): HttpUrl {
        return HttpUrl.parse(BuildConfig.BASE_URL)!!
    }
}