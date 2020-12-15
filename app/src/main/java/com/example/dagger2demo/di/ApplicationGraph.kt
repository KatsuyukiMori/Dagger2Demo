package com.example.dagger2demo.di

import com.example.dagger2demo.MainActivity
import com.example.dagger2demo.data.source.repository.UserRepository
import com.example.dagger2demo.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * @Component makes Dagger create a graph of dependencies
 * ビルド時に実装クラス [DaggerApplicationGraph] を自動生成する
 * 実装クラスをインスタンス化するには、create() か builder() を使用しなければならない
 * （実装クラスのコンストラクタがprivateであるため）
 *
 * このアノテーションを付けたインターフェースを1つのDaggerグラフとして扱う
 * イメージとしては、依存関係注入したクラス（Repository）にアクセスするための入り口
 *
 * @Singleton
 * Scope annotations on a @Component interface informs Dagger that classes annotated
 * with this annotation (i.e. @Singleton) are bound to the life of the graph and so
 * the same instance of that type is provided every time the type is requested.
 * 一意のRepositoryのインスタンスを取得するには、このインターフェースとRepositoryにアノテーションを付ける
 * また、保有するオブジェクトの生存期間をこのコンポーネントの生存期間に制限する
 * よって、好きな場所からこの実装クラスを呼び、一意のRepositoryを取得できる（合ってる？）
 */
@Singleton
// The "modules" attribute in the @Component annotation tells Dagger what Modules
// to include when building the graph
@Component(modules = [NetworkModule::class])
interface ApplicationGraph {
    // The return type  of functions inside the component interface is
    // what can be provided from the container
    fun repository(): UserRepository

    // This tells Dagger that Activity requests injection so the graph needs to
    // satisfy all the dependencies of the fields that Activity is requesting.
    // Activityが一意であるグラフにアクセスし注入を要求することをDaggerに伝える
    // そのために要求元のActivityを引数で伝えてもらう
    fun inject(activity: MainActivity)
}