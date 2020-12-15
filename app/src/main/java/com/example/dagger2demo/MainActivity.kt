package com.example.dagger2demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dagger2demo.ui.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = this::class.java.name
    }

    private lateinit var myApplication: MyApplication

    // 初期化時にonCreateをするため、Repositoryのようにコンストラクタインジェクション（@Inject）ができない
    // よってフィールド インジェクションをして、Daggerに依存関係を注入してもらう
    // Daggerグラフから取得したいフィールドに @Inject アノテーションを適用する
    // You want Dagger to provide an instance of LoginViewModel from the graph
    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        myApplication = applicationContext as MyApplication
        // Make Dagger instantiate @Inject fields in MainActivity
        // フラグメントの復元に関する問題を回避するため、super.onCreate()の前にDaggerを注入する必要がある
        myApplication.applicationGraph.inject(this)
        // Now any injected object(ex.ViewModel) is available
        // ...
        Log.d(TAG, "--onCreate")
        Log.d(
            TAG, "graph:${myApplication.applicationGraph.hashCode()}, " +
                    "repository:${myApplication.userRepository.hashCode()}"
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}