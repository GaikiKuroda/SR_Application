package app.gkuroda.srapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import app.gkuroda.srapplication.dagger.viewModel.ViewModelFactory
import app.gkuroda.srapplication.databinding.FragmentSearchResultBinding
import com.trello.rxlifecycle4.components.support.RxFragment
import dagger.android.support.AndroidSupportInjection
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject


class SearchResultFragment : RxFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainActivityViewModel

    lateinit var recyclerViewAdapter: SearchResultRecyclerViewAdapter

    private lateinit var binding: FragmentSearchResultBinding

    private val disposable = CompositeDisposable()

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
        binding = FragmentSearchResultBinding.inflate(inflater)
        searchResultRecyclerViewSetup()
        subscribeViewModel()

        requestSearch()

        return binding.root
    }

    /**
     * RecyclerViewの初期化などを行います
     */
    private fun searchResultRecyclerViewSetup() {
        recyclerViewAdapter = SearchResultRecyclerViewAdapter(requireContext())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recyclerViewAdapter
        }

    }

    /**
     * Viewmodelの値の更新を監視します
     */
    private fun subscribeViewModel() {
        viewModel.searchResult
            .observeOn(AndroidSchedulers.mainThread())
            .map { rawItem ->
                rawItem.items.map { it.name }
            }
            .subscribe {
                recyclerViewAdapter.resultItems = it
                recyclerViewAdapter.notifyDataSetChanged()
                binding.executePendingBindings()
            }
            .addTo(disposable)
    }

    /**
     * 検索を実行します。
     * 設定されたクエリを一番上に表示します
     */
    private fun requestSearch() {
        val query = "android"
        binding.queryString = query
        viewModel.requestSearch(query)
    }

}