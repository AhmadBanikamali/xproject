package com.abcdandroid.presenter.datalist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.abcdandroid.domain.remote.GetData
import com.abcdandroid.presenter.datalist.pagination.PassengersDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailListViewModel @Inject constructor(
    private val getData: GetData,
) : ViewModel() {

    lateinit var a: LiveData<PagingData<String>>

    fun getData() {
        viewModelScope.launch {
            a = Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
                PassengersDataSource(getData)
            }).liveData
        }
    }

}