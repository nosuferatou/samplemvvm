package com.example.mvvmproject.ui

import android.app.Application
import com.example.mvvmproject.data.network.ApiService
import com.example.mvvmproject.data.repository.MovieRepository
import com.example.mvvmproject.viewmodel.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class MyApplication : Application() {

    val appModule = module {
        single { ApiService.create() }
        // Menyatakan MovieRepository sebagai singleton, hanya satu instance yang digunakan
        single { MovieRepository(get()) }
        // Menyatakan MovieViewModel dan memberinya dependensi MovieRepository
        viewModel { MovieViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        // Mulai Koin dan tambahkan modul
        startKoin {
            androidContext(this@MyApplication) // Konteks aplikasi
            modules(appModule) // Menambahkan modul Koin
        }
    }
}