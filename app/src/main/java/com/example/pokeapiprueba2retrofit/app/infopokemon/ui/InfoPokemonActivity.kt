package com.example.pokeapiprueba2retrofit.app.infopokemon.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokeapiprueba2retrofit.app.api.PokeApi
import com.example.pokeapiprueba2retrofit.app.infopokemon.viewmodel.InfoPokemonViewModel
import com.example.pokeapiprueba2retrofit.databinding.ActivityInfoPokemonBinding
import kotlinx.coroutines.launch

class InfoPokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoPokemonBinding
    private lateinit var viewModel: InfoPokemonViewModel

    private val defaultValue = 0.0
    private val HEC_TO_LB = 4.536
    private val HEC_TO_KG = 10.0
    private val HEC_TO_OZ = 3.527
    private val DM_TO_M = 10.0
    private val DM_TO_FT = 3.048
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[InfoPokemonViewModel::class.java]

        val intent = intent
        val pokemonId = intent.getIntExtra("id", 0)

        viewModel.getSprites(pokemonId + 1)

        lifecycleScope.launch {
            viewModel.sprites1.collect { sprites1 ->
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
        }

        lifecycleScope.launch {
            viewModel.pokemonName.collect { name ->
                binding.tvName.text = name?.substring(0, 1)?.toUpperCase() + name?.substring(1)
            }
        }

        lifecycleScope.launch {
            viewModel.pokemonWeight.collect { weight ->
                binding.apply {
                    tvPeso.text =
                        viewModel.divFormat(weight ?: defaultValue.toInt(), HEC_TO_LB, " lb")
                    cvKg.setOnClickListener {
                        tvPeso.text =
                            viewModel.divFormat(weight ?: defaultValue.toInt(), HEC_TO_KG, " kg")
                    }
                    cvLb.setOnClickListener {
                        tvPeso.text =
                            viewModel.divFormat(weight ?: defaultValue.toInt(), HEC_TO_LB, " lb")
                    }
                    cvOnz.setOnClickListener {
                        tvPeso.text =
                            viewModel.mulFormat(weight ?: defaultValue.toInt(), HEC_TO_OZ, " oz")
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.pokemonHeight.collect { height ->
                binding.apply {
                    tvAlto.text =
                        viewModel.divFormat(height ?: defaultValue.toInt(), DM_TO_FT, " ft")
                    cvMetros.setOnClickListener {
                        tvAlto.text =
                            viewModel.divFormat(height ?: defaultValue.toInt(), DM_TO_M, " m")
                    }
                    cvPies.setOnClickListener {
                        tvAlto.text =
                            viewModel.divFormat(height ?: defaultValue.toInt(), DM_TO_FT, " ft")
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.pokemonTypes.collect { types ->
                binding.tvTypes.text = types?.joinToString(", ")
            }
        }

        lifecycleScope.launch {
            viewModel.pokemonAbilities.collect { abilities ->
                binding.tvAbilities.text = abilities?.joinToString(", ")
            }
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

    private fun initialImage(image: String) {
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