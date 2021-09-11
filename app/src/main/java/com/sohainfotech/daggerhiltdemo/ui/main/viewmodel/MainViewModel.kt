package com.sohainfotech.daggerhiltdemo.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sohainfotech.daggerhiltdemo.data.model.User
import com.sohainfotech.daggerhiltdemo.data.repository.MainRepository
import com.sohainfotech.daggerhiltdemo.utils.NetworkHelper
import com.sohainfotech.daggerhiltdemo.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//Step 08.

/**
 * Now, to pass NetworkHelper and MainRepository we won't use ViewModelFactory
 * here but will directly pass both of them and use the @ViewModelInject annotation like,
 */

/**
 * Here, ViewModelInject annotation will inject the dependency using the constructor
 * and now we will perform the operations inside MainViewModel like,
 */

/**
 * Here, we are fetching the users in the init block and inside the viewModelScope,
 * we will check for internet connectivity and if the connectivity is ok then we go through the API call or else we set the value to LiveData with an error.

This user LiveData is then observed in the MainActivity to display the items in the recyclerView.

If you see in the above steps, we get the instance of ViewModel by using by viewModels()

The ViewModel which is annotated by @ViewModelInject can only be reference by Views which are annotated by @AndroidEntryPoint

 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()

    val users: LiveData<Resource<List<User>>>
        get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {

        viewModelScope.launch {
            _users.postValue(Resource.loading(null))

            if (networkHelper.isNetworkConnected()) {
                mainRepository.getUsers().let {
                    if (it.isSuccessful) {
                        _users.postValue(Resource.success(it.body()))
                    } else {
                        _users.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else {
                _users.postValue(Resource.error("No internet connection", null))
            }
        }
    }

}
