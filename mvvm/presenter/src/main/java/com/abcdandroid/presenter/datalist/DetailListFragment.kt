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


@AndroidEntryPoint
class DetailListFragment : Fragment() {

    private val viewModel by viewModels<DetailListViewModel>()

    private lateinit var binding: FragmentDetailListBinding

    var passengersAdapter: PassengersAdapter = PassengersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.item, viewModel)
        viewModel.getData()

        viewModel.pagingData.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                passengersAdapter.submitData(it)
            }
        }




        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = passengersAdapter.also {
                it.setOnCheckboxClick(viewModel.itemStateArray, viewModel::onCheckChange)
            }
        }

        return binding.root
    }


}