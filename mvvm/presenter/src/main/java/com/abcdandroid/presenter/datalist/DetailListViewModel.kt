package com.abcdandroid.presenter.datalist

import android.util.SparseBooleanArray
import androidx.lifecycle.*
import androidx.paging.*
import com.abcdandroid.domain.local.GetData
import com.abcdandroid.domain.local.RemoveData
import com.abcdandroid.domain.local.SaveData
import com.abcdandroid.presenter.datalist.pagination.PassengersDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailListViewModel @Inject constructor(
    getSavedData: GetData,
    private val saveData: SaveData,
    private val removeData: RemoveData,
    private val  passengersDataSource: PassengersDataSource
) : ViewModel() {

    var filteredPagingData: MutableLiveData<PagingData<String>> = MutableLiveData()

    lateinit var pagingData: LiveData<PagingData<String>>
    val favoriteItems: LiveData<List<String>> = getSavedData()
    val a = MediatorLiveData<Boolean> ()

    val isFavoritesVisible: MutableLiveData<Boolean> = MutableLiveData(true)

    fun getRemoteData() {
        viewModelScope.launch {
            pagingData = Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
                    passengersDataSource
                }).liveData.cachedIn(viewModelScope)
        }
    }



    fun onCheckChange(text: String, isChecked: Boolean) {
            viewModelScope.launch {
                if (isChecked)
                    saveData(text)
                else
                    removeData(text)
            }

    }

    fun onFavorites(a: List<String>) {

    }



    fun showFavoritesState() {
        isFavoritesVisible.value = isFavoritesVisible.value?.not()
    }

}