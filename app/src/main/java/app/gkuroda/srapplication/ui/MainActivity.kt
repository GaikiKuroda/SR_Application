package app.gkuroda.srapplication.ui

import android.os.Bundle
import app.gkuroda.srapplication.R
import app.gkuroda.srapplication.dagger.viewModel.ViewModelFactory
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : RxAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        viewModel = viewModelFactory.get(this)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, SearchResultFragment())
            .commit()
    }
}