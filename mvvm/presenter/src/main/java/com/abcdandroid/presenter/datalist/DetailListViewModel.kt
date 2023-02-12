package com.abcdandroid.presenter.datalist

import android.util.SparseBooleanArray
import androidx.lifecycle.MutableLiveData
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

    lateinit var pagingData: MutableLiveData<PagingData<String>>
    val itemStateArray: SparseBooleanArray = SparseBooleanArray()
    var isFavorites = MutableLiveData(false)

    fun getData() {
        viewModelScope.launch {
            pagingData =
                Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
                    PassengersDataSource(getData, isFavorites.value ?: false)
                }).liveData as MutableLiveData<PagingData<String>>
        }
    }

    fun onCheckChange(position: Int,text:String, isChecked: Boolean) {
        itemStateArray.put(position, isChecked)
        println(text)
    }

    fun onFavorites(a: SparseBooleanArray) {
        println(a.size())
    }

    fun showFavoritesState() {
        isFavorites.value = isFavorites.value?.not()
    }

}