package app.gkuroda.srapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.gkuroda.srapplication.R
import app.gkuroda.srapplication.dagger.viewModel.ViewModelFactory
import com.trello.rxlifecycle4.components.support.RxFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class SearchResultFragment : RxFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)

        val baseActivity = activity as MainActivity
        viewModel = viewModelFactory.get(baseActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_result, container, false)
    }

}