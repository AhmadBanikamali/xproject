package com.abcdandroid.domain.local

import androidx.lifecycle.LiveData
import com.abcdandroid.domain.Repository
import javax.inject.Inject


class GetData @Inject constructor (private val repository: Repository) {

    operator fun invoke(): LiveData<List<String>> {
        return repository.getSavedData()
    }
}