package com.example.wafarlytask.di

import com.example.wafarlytask.data.ApiService
import com.example.wafarlytask.data.DataRepo
import com.example.wafarlytask.utils.RetrofitObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {



    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return RetrofitObject.getNetworkInstance!!
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


//    @Provides
//    @ViewModelScoped // this is new
//    fun dataRepo: DataRepo(){
//        return DataRepo()
//    }

}