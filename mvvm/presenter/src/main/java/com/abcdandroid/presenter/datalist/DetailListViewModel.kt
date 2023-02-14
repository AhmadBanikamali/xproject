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
    getData: com.abcdandroid.domain.remote.GetData,
    private val saveData: SaveData,
    private val removeData: RemoveData,
) : ViewModel() {

    var filteredPagingData: MutableLiveData<PagingData<String>> = MutableLiveData()

    private val  passengersDataSource: PassengersDataSource = PassengersDataSource(getData)
    lateinit var pagingData: LiveData<PagingData<String>>
    val favoriteItems: LiveData<List<String>> = getSavedData()

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


    fun showFavoritesState() {
        isFavoritesVisible.value = isFavoritesVisible.value?.not()
    }

}