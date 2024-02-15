package com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote

import com.google.gson.annotations.SerializedName

data class InfoAbilitiesResponse(
    @SerializedName("effect_changes") val effectChanges: ArrayList<String>,
    @SerializedName("effect_entries") val effectEntries: List<AbilityEffectEntriesResponse>,
    @SerializedName("flavor_text_entries") val flavorTextEntries: ArrayList<AbilityFlavorTextEntriesResponse> = arrayListOf(),
    @SerializedName("generation") val generation: AbilityGenerationResponse? = AbilityGenerationResponse(),
    @SerializedName("id") val id: Int? = null,
    @SerializedName("is_main_series") val isMainSeries: Boolean? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("names") val names: ArrayList<AbilityNamesResponse> = arrayListOf(),
    @SerializedName("pokemon") val pokemon: ArrayList<AbilityPokemonResponse> = arrayListOf()
)

data class AbilityLanguageResponse(
    @SerializedName("name") val name: String? = "",
    @SerializedName("url") val url: String? = ""

)

data class AbilityEffectEntriesResponse(

    @SerializedName("effect") val effect: String,
    @SerializedName("language") val language: AbilityLanguageResponse?,
    @SerializedName("short_effect") val shortEffect: String

)

data class AbilityVersionGroupResponse(

    @SerializedName("name") val name: String? = null,
    @SerializedName("url") val url: String? = null

)

data class AbilityFlavorTextEntriesResponse(

    @SerializedName("flavor_text") val flavorText: String? = null,
    @SerializedName("language") val language: AbilityLanguageResponse? = AbilityLanguageResponse(),
    @SerializedName("version_group") val versionGroup: AbilityVersionGroupResponse? = AbilityVersionGroupResponse()

)

data class AbilityGenerationResponse(

    @SerializedName("name") val name: String? = null,
    @SerializedName("url") val url: String? = null

)

data class AbilityNamesResponse(

    @SerializedName("language") val language: AbilityLanguageResponse? = AbilityLanguageResponse(),
    @SerializedName("name") val name: String? = null

)

data class AbilityPokemonResponse(

    @SerializedName("name") val name: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("slot") val slot: Int? = null

)
