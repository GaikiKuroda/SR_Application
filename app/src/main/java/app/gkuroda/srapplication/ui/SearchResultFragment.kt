package app.gkuroda.srapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import app.gkuroda.srapplication.databinding.FragmentSearchResultBinding
import com.kaopiz.kprogresshud.KProgressHUD
import com.trello.rxlifecycle4.components.support.RxFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class SearchResultFragment : RxFragment() {

    private val viewModel: MainActivityViewModel by activityViewModel<MainActivityViewModel>()

    lateinit var recyclerViewAdapter: SearchResultRecyclerViewAdapter

    private lateinit var binding: FragmentSearchResultBinding

    private val disposable = CompositeDisposable()

    // プログレス表示
    private val progress: KProgressHUD by lazy {
        KProgressHUD.create(requireContext())
            .setCancellable(false)
            .setAnimationSpeed(1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                progress.dismiss()

                recyclerViewAdapter.resultItems = it
                recyclerViewAdapter.notifyDataSetChanged()
                binding.executePendingBindings()
            }
            .addTo(disposable)

        viewModel.searchError
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                openErrorToast(it)
                progress.dismiss()
            }
            .addTo(disposable)

    }

    /**
     * 検索を実行します。
     * 設定されたクエリを一番上に表示します
     */
    private fun requestSearch() {
        val query = "Android"
        progress.show()
        binding.queryString = query
        viewModel.requestSearch(query)
    }

    /** エラーが発生した場合トーストを出します */
    private fun openErrorToast(exception: Exception) {
        Toast.makeText(requireContext(), "エラー：$exception", Toast.LENGTH_LONG).show()
    }

}