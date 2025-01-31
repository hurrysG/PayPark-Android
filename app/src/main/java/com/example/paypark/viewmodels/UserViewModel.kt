package com.example.paypark.viewmodels
//https://github.com/hurrysG/PayPark-Android.git
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.paypark.database.PayParkDatabase
import com.example.paypark.database.UserRepository
import com.example.paypark.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepo: UserRepository
    var allUsers: LiveData<List<User>>

    init {
        val userDao = PayParkDatabase.getDatabase(application).userDao()
        userRepo = UserRepository(userDao)

        allUsers = userRepo.allUsers
    }

    /**
     * insertAll() method will create a new user record in the databaseee
     */
    fun insertAll(user : User) =  viewModelScope.launch(Dispatchers.IO){
        userRepo.insertAll(user)
    }
    fun updateUser(user : User) = viewModelScope.launch(Dispatchers.IO){
        userRepo.updateUser(user)
    }
    fun deleteUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        userRepo.deleteUser(user)
    }
    fun deleteUserByEmail(email: String) = viewModelScope.launch(Dispatchers.IO) {
        userRepo.deleteUserByEmail(email)
    }
}