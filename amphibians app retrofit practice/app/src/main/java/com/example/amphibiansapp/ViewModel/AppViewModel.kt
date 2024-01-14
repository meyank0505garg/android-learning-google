package com.example.amphibiansapp.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibiansapp.data.DataStructure
import com.example.amphibiansapp.network.AmphibiansAppApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface AppState{
    data class Success(
        val data :List<DataStructure>


    ) : AppState

    object Error : AppState
    object Loading : AppState


}
class AppViewModel : ViewModel() {

    var appState : AppState by mutableStateOf(AppState.Loading)
        private set

    fun getdata () {
        viewModelScope.launch{
            appState = AppState.Loading

            appState = try{
                val listResult = AmphibiansAppApi.retrofitService.getFullData()
                AppState.Success(
                    data = listResult
                )



            }catch (e : IOException) {
                AppState.Error

            }catch (e: HttpException) {
                AppState.Error
            }




        }
    }

    init {
        getdata()
    }


}