package com.example.pokeapiprueba2retrofit.main.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapiprueba2retrofit.main.domain.remote.PokemonResponse
import com.example.pokeapiprueba2retrofit.main.domain.model.pokemonform.PokemonFormModel
import com.example.pokeapiprueba2retrofit.main.domain.model.pokemonform.Sprites
import com.example.pokeapiprueba2retrofit.main.domain.remote.PokemonsResultResponse
import com.example.pokeapiprueba2retrofit.main.retrofit.RetrofitModule.getInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel() : ViewModel() {

    val pokemonsVM: MutableLiveData<List<PokemonsResultResponse>> = MutableLiveData()
    val imagePokemon: MutableLiveData<Sprites?> = MutableLiveData()

    fun getPokemonsByLimitAndOffset(limit:Int, offset:Int) {
        viewModelScope.launch(Dispatchers.IO) {

            getInstance().getPokemonsByLimitAndOffset(limit, offset).enqueue(object : Callback<PokemonResponse> {
                override fun onResponse(
                    call: Call<PokemonResponse>,
                    response: Response<PokemonResponse>) {
                    if (response.isSuccessful) {
                        pokemonsVM.value = response.body()?.results ?: emptyList()
                    } else {
                        pokemonsVM.value = emptyList()
                    }

                }

                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {

                }
            })
        }
    }

    fun getPokemonsById(id:Int) {
        CoroutineScope(Dispatchers.IO).launch {

                getInstance().getPokemonById(id).enqueue(object : Callback<PokemonFormModel> {

                    override fun onResponse(
                        call: Call<PokemonFormModel>,
                        response: Response<PokemonFormModel>
                    ) {
                        if (response.isSuccessful) {
                            imagePokemon.value = response.body()?.sprites
                            Log.d("MARIO", response.body().toString())
                        } else {
                            imagePokemon.value = null
                        }
                    }

                    override fun onFailure(call: Call<PokemonFormModel>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })

        }
    }
}