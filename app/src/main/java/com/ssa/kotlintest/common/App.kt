package com.ssa.kotlintest.common

import android.app.Application
import android.arch.persistence.room.Room
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ssa.kotlintest.common.Constants.Companion.DATABASE_NAME
import com.ssa.kotlintest.data.network.ServiceApi
import com.ssa.kotlintest.interactors.NetworkInteractor
import com.ssa.kotlintest.interactors.NetworkInteractorImpl
import com.ssa.kotlintest.screens.main.MainPresenter
import com.ssa.kotlintest.screens.main.MainPresenterImpl
import okhttp3.OkHttpClient
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object {
        val TAG = App::class.simpleName
    }

    private val mAppModule: Module = org.koin.dsl.module.applicationContext {
        factory { MainPresenterImpl(get()) as MainPresenter }

        bean { NetworkInteractorImpl(get()) as NetworkInteractor }
        bean { createRetrofit(get()) }
        bean { GsonBuilder().create() }
        bean { createServiceApi(get()) }
        bean { createRoom() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(mAppModule))

        val defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler({ thread: Thread, throwable: Throwable ->
            defaultUncaughtExceptionHandler.uncaughtException(thread, throwable)
            Log.e(TAG, throwable.message)
        })
    }

    private fun createServiceApi(retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }

    private fun createRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .client(OkHttpClient())
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    private fun createRoom(): AppDatabase {
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java,
                DATABASE_NAME)
                .fallbackToDestructiveMigration().build()
    }
}