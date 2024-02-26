package com.example.pokeapiprueba2retrofit.app.main.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokeapiprueba2retrofit.R
import com.example.pokeapiprueba2retrofit.app.main.data.model.PokemonsResultModel
import com.example.pokeapiprueba2retrofit.app.main.viewmodel.PokemonViewModel
import com.example.pokeapiprueba2retrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PokemonViewModel
    private lateinit var myAdapter: PokemonAdapter
    private var offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        observer()

        viewModel.getPokemonsByLimitAndOffset(offset)

        refresh()

    }

    private fun observer() {
        lifecycleScope.launch {
            viewModel.pokemonsVM.collect { pokemons ->
                recyclerConfigurations(pokemons ?: mutableListOf())
            }
        }
        lifecycleScope.launch {
            viewModel.swipeRefresh.collect { refresh ->
                binding.swipeRefreshLayout.isRefreshing = refresh == true
            }
        }
    }

    private fun recyclerConfigurations(pokemons: MutableList<PokemonsResultModel>) {
        with(binding.rv) {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            myAdapter = PokemonAdapter(pokemons)
            adapter = myAdapter
            binding.sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        // Filtrar la lista de Pokemon según el texto de búsqueda y convertirla a MutableList
                        val filteredList: MutableList<PokemonsResultModel> =
                            pokemons.filter { pokemon ->
                                pokemon.name.contains(it, ignoreCase = true)
                            }.toMutableList()
                        // Actualizar el adaptador con la lista filtrada
                        myAdapter.updatePokemonList(filteredList)
                    }
                    return false
                }
            })

        }

    }

    private fun refresh(){
        binding.apply {
            swipeRefreshLayout.setProgressBackgroundColorSchemeColor(resources.getColor(R.color.asas))
            swipeRefreshLayout.setColorSchemeColors(resources.getColor(R.color.textGreen))
            swipeRefreshLayout.setOnRefreshListener {
                loadDataAndRefresh()
            }

        }

    }

    private fun loadDataAndRefresh() {
        // Simula una operación de carga de datos
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getPokemonsByLimitAndOffset(offset)
            // Aquí debes cargar tus datos, por ejemplo, desde una base de datos o una API
            // Una vez que los datos están cargados, actualiza el RecyclerView
            // Asegúrate de llamar a swipeRefreshLayout.setRefreshing(false) para detener la animación de refresco
            binding.swipeRefreshLayout.isRefreshing = false
            // Notifica a tu Adapter que los datos han cambiado
            myAdapter.notifyDataSetChanged()
        },  2000) // Simula un retardo de  2 segundos
    }


}