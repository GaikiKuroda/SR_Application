package app.gkuroda.srapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.gkuroda.srapplication.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel<MainActivityViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 現状ココで呼び出す必要はないが、呼び出さないとインスタンスができないためココで呼ぶ
        viewModel

        supportFragmentManager.beginTransaction()
            .add(R.id.container, SearchResultFragment())
            .commit()
    }
}
