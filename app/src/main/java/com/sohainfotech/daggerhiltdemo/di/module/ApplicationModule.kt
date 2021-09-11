package com.sohainfotech.daggerhiltdemo.di.module

import androidx.core.os.BuildCompat
import com.sohainfotech.daggerhiltdemo.BuildConfig
import com.sohainfotech.daggerhiltdemo.data.api.ApiHelper
import com.sohainfotech.daggerhiltdemo.data.api.ApiHelperImpl
import com.sohainfotech.daggerhiltdemo.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

//Step 08.
/**
 * As you can see we are not creating ApplicationComponent as we will use the one provided by Dagger-Hilt itself.
 */

/**
 * We will create a class ApplicationModule and annotate it with @Module.
 * Using this annotation will make dagger understand that this class is a module.
 */

/**
 * Now, we will need to plug this module class in the specific component.
 * In this case, we need to this at the application level so we will install it in ApplicationComponent like,
 */

/**
 * @Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {}
 */

/**
 * This means that the dependencies provided here will be used across the application. Let's consider that we want to use at the activity level we install the module in,

@InstallIn(ActivityComponent::class)
Similarly like ApplicationComponent/ActivityComponent, we have a different type of components like,

FragmentComponent for Fragments, ServiceComponent for Service, etc.
 */

/**
 * Now, inside ApplicationModule, we will provide all the dependencies one by one
 * and the updated code of ApplicationModule class looks like,
 */

/**
 * Here, we have provided dependencies using @Provide annotation, which would be accessed across the application.

@Singleton annotation helps the instance to be created and used once across the app.
 */

/**
 * Similarly, like Singleton which stays till the application lifecycle,
 * we also have @ActivityScoped, @FragmentScoped,
 * etc in which dependencies are scoped till the lifecycle of Activity and Fragment.
 */

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelperImpl: ApiHelperImpl): ApiHelper = apiHelperImpl

}