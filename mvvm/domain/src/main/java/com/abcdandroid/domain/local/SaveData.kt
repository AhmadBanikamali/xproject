package com.abcdandroid.domain.local

import com.abcdandroid.domain.Repository
import javax.inject.Inject

class SaveData @Inject constructor (private val repository: Repository) {

   suspend operator fun invoke(data:String){
        return repository.addDataToFavorite(data)
    }
}