package id.alianhakim.rickmortyapp.feature_character.presentation.character_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.alianhakim.rickmortyapp.R
import id.alianhakim.rickmortyapp.databinding.FragmentCharacterListBinding
import id.alianhakim.rickmortyapp.feature_character.presentation.character_list.adapter.CharacterListPagedAdapter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private val viewModel by viewModels<CharacterListViewModel>()
    private val mAdapter by lazy { CharacterListPagedAdapter() }
    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        setupRecyclerView()
        loadingPagedData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    private fun loadingPagedData() {
        lifecycleScope.launch {
            viewModel.listData.collect { pagingData ->
                mAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.characterListRv.apply {
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
            adapter = mAdapter
            setHasFixedSize(true)
        }
        mAdapter.setOnItemClickListener {
            val bundle = Bundle()
            bundle.putSerializable("character", it)
            findNavController().navigate(
                R.id.action_characterListFragment_to_characterDetailFragment,
                bundle
            )
        }
    }

    private fun showLoading() {
        with(binding) {
            hideError()
            loading.visibility = View.VISIBLE
            characterListRv.visibility = View.GONE
        }
    }

    private fun hideLoading() {
        with(binding) {
            loading.visibility = View.GONE
            characterListRv.visibility = View.VISIBLE
        }
    }

    private fun showError(msg: String) {
        with(binding) {
            hideLoading()
            error.visibility = View.VISIBLE
            error.text = msg
            retryBtn.visibility = View.VISIBLE
        }
    }

    private fun hideError() {
        with(binding) {
            error.visibility = View.GONE
            retryBtn.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}