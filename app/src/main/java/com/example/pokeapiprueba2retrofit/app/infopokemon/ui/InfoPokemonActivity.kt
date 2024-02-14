package com.example.pokeapiprueba2retrofit.app.infopokemon.ui

import android.media.AudioAttributes
import android.media.MediaPlayer
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
import java.io.IOException

class InfoPokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoPokemonBinding
    private lateinit var viewModel: InfoPokemonViewModel
    lateinit var mediaPlayer: MediaPlayer

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

        playAudioFromUrl(PokeApi.urlSound(pokemonId + 1))
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

        lifecycleScope.launch {
            viewModel.pokemonStatHP.collect { hp ->
                binding.pbHP.progress = hp ?: 0
                binding.tvHP.text = hp.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.pokemonStatAttack.collect { attack ->
                binding.pbAttack.progress = attack ?: 0
                binding.tvAttack.text = attack.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.pokemonStatDefense.collect { defense ->
                binding.pbDefense.progress = defense ?: 0
                binding.tvDefense.text = defense.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.pokemonStatSpeed.collect { speed ->
                binding.pbSpeed.progress = speed ?: 0
                binding.tvSpeed.text = speed.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.pokemonStatSpecialAttack.collect { specialAttack ->
                binding.pbAttackS.progress = specialAttack ?: 0
                binding.tvAttackS.text = specialAttack.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.pokemonStatSpecialDefense.collect { specialDefense ->
                binding.pbDefenseS.progress = specialDefense ?: 0
                binding.tvDefenseS.text = specialDefense.toString()
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

        binding.ibSound.setOnClickListener { playAudioFromUrl(PokeApi.urlSound(pokemonId + 1)) }

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

    fun playAudioFromUrl(url: String) {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setOnPreparedListener {
                // Cuando el MediaPlayer esté listo, comenzamos a reproducir
                start()
            }
        }

        try {
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepareAsync()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

}