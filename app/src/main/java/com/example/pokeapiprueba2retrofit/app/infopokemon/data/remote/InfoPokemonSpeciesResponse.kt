package com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote


import com.google.gson.annotations.SerializedName

data class InfoPokemonSpeciesResponse(
    @SerializedName("base_happiness")
    val baseHappiness: Int?,
    @SerializedName("capture_rate")
    val captureRate: Int?,
    @SerializedName("color")
    val color: ColorResponse?,
    @SerializedName("egg_groups")
    val eggGroups: List<EggGroupResponse>?,
    @SerializedName("evolution_chain")
    val evolutionChain: EvolutionChainResponse?,
    @SerializedName("evolves_from_species")
    val evolvesFromSpecies: EvolvesFromSpeciesResponse?,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntryResponse>?,
    @SerializedName("form_descriptions")
    val formDescriptions: List<Any>?,
    @SerializedName("forms_switchable")
    val formsSwitchable: Boolean?,
    @SerializedName("gender_rate")
    val genderRate: Int?,
    @SerializedName("genera")
    val genera: List<GeneraResponse>?,
    @SerializedName("generation")
    val generation: GenerationResponse?,
    @SerializedName("growth_rate")
    val growthRate: GrowthRateResponse?,
    @SerializedName("habitat")
    val habitat: HabitatResponse?,
    @SerializedName("has_gender_differences")
    val hasGenderDifferences: Boolean?,
    @SerializedName("hatch_counter")
    val hatchCounter: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_baby")
    val isBaby: Boolean?,
    @SerializedName("is_legendary")
    val isLegendary: Boolean?,
    @SerializedName("is_mythical")
    val isMythical: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("names")
    val names: List<NameResponse>?,
    @SerializedName("order")
    val order: Int?,
    @SerializedName("pal_park_encounters")
    val palParkEncounters: List<PalParkEncounterResponse>?,
    @SerializedName("pokedex_numbers")
    val pokedexNumbers: List<PokedexNumberResponse>?,
    @SerializedName("shape")
    val shape: ShapeResponse?,
    @SerializedName("varieties")
    val varieties: List<VarietyResponse>?
)

data class ColorResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)

data class EggGroupResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)

data class EvolutionChainResponse(
    @SerializedName("url")
    val url: String?
)

data class EvolvesFromSpeciesResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)

data class FlavorTextEntryResponse(
    @SerializedName("flavor_text")
    val flavorText: String?,
    @SerializedName("language")
    val language: LanguageResponse?,
    @SerializedName("version")
    val version: VersionSpeciesResponse?
)
data class VersionSpeciesResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)
data class LanguageResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)

data class GeneraResponse(
    @SerializedName("genus")
    val genus: String?,
    @SerializedName("language")
    val language: LanguageResponse?
)

data class GenerationResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)

data class GrowthRateResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)

data class HabitatResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)

data class NameResponse(
    @SerializedName("language")
    val language: LanguageResponse?,
    @SerializedName("name")
    val name: String?
)

data class PalParkEncounterResponse(
    @SerializedName("area")
    val area: AreaResponse?,
    @SerializedName("base_score")
    val baseScore: Int?,
    @SerializedName("rate")
    val rate: Int?
)
data class AreaResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)

data class PokedexNumberResponse(
    @SerializedName("entry_number")
    val entryNumber: Int?,
    @SerializedName("pokedex")
    val pokedex: PokedexResponse?
)
data class PokedexResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)

data class ShapeResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)

data class VarietyResponse(
    @SerializedName("is_default")
    val isDefault: Boolean?,
    @SerializedName("pokemon")
    val pokemon: PokemonResponse?
)
data class PokemonResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)