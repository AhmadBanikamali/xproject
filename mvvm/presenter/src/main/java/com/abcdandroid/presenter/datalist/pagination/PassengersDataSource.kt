package com.abcdandroid.presenter.datalist.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.abcdandroid.domain.remote.GetData
import javax.inject.Inject

class PassengersDataSource @Inject constructor(private val getData: GetData) : PagingSource<Int, String>() {



    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        var pageNumber = params.key ?: 1
        return try {
            val response = getData(pageNumber)

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = ++pageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, String>): Int? {
        TODO("Not yet implemented")
    }


}