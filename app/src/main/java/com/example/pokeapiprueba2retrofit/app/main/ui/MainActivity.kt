package com.example.pokeapiprueba2retrofit.app.main.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapiprueba2retrofit.databinding.ActivityMainBinding
import com.example.pokeapiprueba2retrofit.app.main.data.model.PokemonsResultModel
import com.example.pokeapiprueba2retrofit.app.main.viewmodel.PokemonViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PokemonViewModel
    private lateinit var myAdapter: PokemonAdapter
    private var aptoParaCargar = true
    private var offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PokemonViewModel::class.java]

        recyclerConfigurations(mutableListOf())
        observer()

        viewModel.getPokemonsByLimitAndOffset(offset)

    }

    private fun observer() {
        lifecycleScope.launch {
            viewModel.pokemonsVM.collect { pokemons ->
                myAdapter.adicionarLista(pokemons ?: mutableListOf())
                aptoParaCargar = true
            }
        }
    }

    private fun recyclerConfigurations(pokemons: MutableList<PokemonsResultModel>) {
        with(binding.rv) {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            myAdapter = PokemonAdapter(pokemons)
            adapter = myAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        val visibleItemCount = (layoutManager as GridLayoutManager).childCount
                        val totalItemCount = (layoutManager as GridLayoutManager).itemCount
                        val pastVisibleItems: Int =
                            (layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
                        if (aptoParaCargar) {
                            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                                Log.i("MARIO", " Llegamos al final.")
                                aptoParaCargar = false
                                offset += 60
                                viewModel.getPokemonsByLimitAndOffset(offset)
                            }
                        }
                    }
                }
            })
        }

    }

}