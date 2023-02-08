package com.abcdandroid.domain.remote

import com.abcdandroid.domain.Repository
import javax.inject.Inject


class GetData @Inject constructor (private val repository: Repository) {

    suspend operator fun invoke(pageNumber:Int): List<String> {
        return repository.getRemoteData(pageNumber)
    }
}