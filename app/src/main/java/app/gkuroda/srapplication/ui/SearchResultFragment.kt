package app.gkuroda.srapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import app.gkuroda.srapplication.databinding.FragmentSearchResultBinding
import app.gkuroda.srapplication.flux.api.SearchResponse
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.failure
import com.github.kittinunf.result.success
import com.kaopiz.kprogresshud.KProgressHUD
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class SearchResultFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModel<MainActivityViewModel>()

    lateinit var recyclerViewAdapter: SearchResultRecyclerViewAdapter

    private lateinit var binding: FragmentSearchResultBinding

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
        val searchResultObserver = Observer<Result<SearchResponse, Exception>> { result ->
            progress.dismiss()
            result.success { successResponse ->
                recyclerViewAdapter.resultItems = successResponse.items.map { it.name }
                recyclerViewAdapter.notifyDataSetChanged()
                binding.executePendingBindings()
            }
            result.failure {
                openErrorToast(it)
                progress.dismiss()
            }
        }

        viewModel.searchResult.observe(this.viewLifecycleOwner, searchResultObserver)

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