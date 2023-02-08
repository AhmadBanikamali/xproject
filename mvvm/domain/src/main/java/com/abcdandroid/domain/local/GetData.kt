package com.abcdandroid.domain.local

import com.abcdandroid.domain.Repository
import javax.inject.Inject


class GetData @Inject constructor (private val repository: Repository) {

    suspend operator fun invoke(): List<String> {
        return repository.getSavedData()
    }
}