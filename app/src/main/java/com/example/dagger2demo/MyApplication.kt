package com.example.dagger2demo

import android.app.Application
import android.util.Log
import com.example.dagger2demo.data.source.repository.UserRepositoryImpl
import com.example.dagger2demo.di.ApplicationGraph
import com.example.dagger2demo.di.DaggerApplicationGraph
import com.example.dagger2demo.di.module.MyApplicationModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

// applicationGraph lives in the Application class to share its lifecycle
class MyApplication: Application(), HasAndroidInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    companion object {
        private val TAG = this::class.java.name
    }

    // Create an instance of the application graph
//    val applicationGraph: ApplicationGraph = DaggerApplicationGraph.create()
    lateinit var appComponent: ApplicationGraph
    // Grab an instance of UserRepository from the application graph
//    val userRepositoryImpl: UserRepositoryImpl = applicationGraph.repository()

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "--onCreate")

        appComponent = DaggerApplicationGraph.builder()
            .application(this)
            .appModule(MyApplicationModule(this))
            .build()

        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }

    private fun <T> compareInstances(instance1: T, instance2: T) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "instance1: $instance1")
            Log.d(TAG, "instance2: $instance2")
            if (instance1 == instance2) {
                Log.d(TAG, "Instances are the same")
            } else {
                Log.d(TAG, "Instances are NOT the same")
            }
        }
    }
}