package com.abcdandroid.presenter.datalist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.abcdandroid.presenter.BR
import com.abcdandroid.presenter.R
import com.abcdandroid.presenter.databinding.FragmentDetailListBinding
import com.abcdandroid.presenter.datalist.adapter.PassengersAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class DetailListFragment : Fragment() {

    private val viewModel by viewModels<DetailListViewModel>()

    private lateinit var binding: FragmentDetailListBinding

    @Inject
    lateinit var passengersAdapter: PassengersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.item, viewModel)
        viewModel.getRemoteData()

        viewModel.pagingData.observe(viewLifecycleOwner) {
            viewModel.filteredPagingData.value = it
        }

        viewModel.filteredPagingData.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                passengersAdapter.submitData(it)
            }
        }

        viewModel.isFavoritesVisible.observe(viewLifecycleOwner) {
            viewModel.filteredPagingData.value =
                if (it) viewModel.pagingData.value else PagingData.from(viewModel.favoriteItems.value?: listOf())
        }

        viewModel.favoriteItems.observe(viewLifecycleOwner, passengersAdapter::setItemsArray)


        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = passengersAdapter.also {
                it.setOnCheckboxClick(viewModel::onCheckChange)
            }
        }

        return binding.root
    }


}