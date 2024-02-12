package com.example.pokeapiprueba2retrofit.app.infopokemon.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokeapiprueba2retrofit.app.api.PokeApi
import com.example.pokeapiprueba2retrofit.app.infopokemon.viewmodel.InfoPokemonViewModel
import com.example.pokeapiprueba2retrofit.app.main.viewmodel.PokemonViewModel
import com.example.pokeapiprueba2retrofit.databinding.ActivityInfoPokemonBinding

class InfoPokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoPokemonBinding
    private lateinit var viewModel: InfoPokemonViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[InfoPokemonViewModel::class.java]

        val intent = intent
        val pokemonId = intent.getIntExtra("id", 0)

        viewModel.getSprites(pokemonId+1)

        viewModel.sprites1.observe(this) { sprites1 ->
            imageSprites(
                sprites1?.frontDefault.toString(),
                sprites1?.frontShiny.toString(),
                sprites1?.backDefault.toString(),
                sprites1?.backShiny.toString()
            )

            binding.apply {
                iv1.setOnClickListener { initialImage(sprites1?.frontDefault.toString()) }
                iv2.setOnClickListener { initialImage(sprites1?.backDefault.toString()) }
                iv3.setOnClickListener { initialImage(sprites1?.frontShiny.toString()) }
                iv4.setOnClickListener { initialImage(sprites1?.backShiny.toString()) }
            }

        }
        viewModel.pokemonName.observe(this) { name ->
            binding.tvName.text = name?.substring(0, 1)?.toUpperCase() + name?.substring(1)
        }
        viewModel.pokemonWeight.observe(this) { weight ->
            binding.apply {
                tvPeso.text = weight.toString().plus(" lb")
                cvKg.setOnClickListener{
                    tvPeso.text = weight?.div(2.205)?.toInt().toString().plus(" kg")
                }
                cvLb.setOnClickListener{
                    tvPeso.text = weight.toString().plus(" lb")
                }
                cvOnz.setOnClickListener{
                    tvPeso.text = weight?.times(16).toString().plus(" oz")
                }
            }
        }
        viewModel.pokemonHeight.observe(this) { height ->
            binding.apply {
                tvAlto.text = height.toString().plus(" ft")
                cvMetros.setOnClickListener {
                    tvAlto.text = height?.div(5.1816)?.toInt().toString().plus(" m")
                }
                cvPies.setOnClickListener {
                    tvAlto.text = height.toString().plus(" ft")
                }
            }

        }
        viewModel.pokemonTypes.observe(this) { types ->
            binding.tvTypes.text = types?.joinToString(", ")
        }
        viewModel.pokemonAbilities.observe(this) {abilities ->
            binding.tvAbilities.text = abilities?.joinToString (", ")
        }

        val requestOptions = RequestOptions()
            .centerCrop()
            .error(android.R.drawable.ic_menu_gallery)
        Glide.with(this)
            .load(PokeApi.url(pokemonId))
            .apply(requestOptions)
            .fitCenter()
            .into(binding.imageView2)

    }

    private fun imageSprites(front: String, frontS: String, back: String, backS: String) {
        val requestOptions = RequestOptions()
            .centerCrop()
            .error(android.R.drawable.ic_menu_gallery)
        Glide.with(this)
            .load(front)
            .apply(requestOptions)
            .fitCenter()
            .into(binding.iv1)
        Glide.with(this)
            .load(back)
            .apply(requestOptions)
            .fitCenter()
            .into(binding.iv2)
        Glide.with(this)
            .load(frontS)
            .apply(requestOptions)
            .fitCenter()
            .into(binding.iv3)
        Glide.with(this)
            .load(backS)
            .apply(requestOptions)
            .fitCenter()
            .into(binding.iv4)
    }

    private fun initialImage(image:String){
        val requestOptions = RequestOptions()
            .centerCrop()
            .error(android.R.drawable.ic_menu_gallery)
        Glide.with(this)
            .load(image)
            .apply(requestOptions)
            .fitCenter()
            .into(binding.imageView2)
    }

}