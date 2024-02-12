package com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote

import com.google.gson.annotations.SerializedName

data class InfoPokemonResponse(
    @SerializedName("abilities")
    val abilities: List<AbilityResponse>,
    @SerializedName("base_experience")
    val baseExperience: Long,
    val cries: CriesResponse,
    val forms: List<FormResponse>,
    @SerializedName("game_indices")
    val gameIndices: List<IndexResponse>,
    val height: Int,
    @SerializedName("held_items")
    val heldItems: List<Any?>,
    val id: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    val moves: List<MfeResponse>,
    val name: String,
    val order: Long,
    @SerializedName("past_abilities")
    val pastAbilities: List<Any?>,
    @SerializedName("past_types")
    val pastTypes: List<Any?>,
    val species: SpeciesResponse,
    val sprites: SpritesResponse,
    val stats: List<StatResponse>,
    @SerializedName("types")
    val types: List<TypeResponse>,
    val weight: Int,
)
data class AbilityResponse(
    val ability: Ability2Response,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Long,
)

data class Ability2Response(
    val name: String,
    val url: String,
)

data class CriesResponse(
    val latest: String,
    val legacy: String,
)

data class FormResponse(
    val name: String,
    val url: String,
)

data class IndexResponse(
    @SerializedName("game_index")
    val gameIndex: Long,
    val version: VersionResponse,
)

data class VersionResponse(
    val name: String,
    val url: String,
)

data class MfeResponse(
    val move: MoveResponse,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetailResponse>,
)

data class MoveResponse(
    val name: String,
    val url: String,
)

data class VersionGroupDetailResponse(
    @SerializedName("level_learned_at")
    val levelLearnedAt: Long,
    @SerializedName("move_learn_method")
    val moveLearnMethod: MoveLearnMethodResponse,
    @SerializedName("version_group")
    val versionGroup: VersionGroupResponse,
)

data class MoveLearnMethodResponse(
    val name: String,
    val url: String,
)

data class VersionGroupResponse(
    val name: String,
    val url: String,
)

data class SpeciesResponse(
    val name: String,
    val url: String,
)

data class SpritesResponse(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_female")
    val backFemale: Any?,
    @SerializedName("back_shiny")
    val backShiny: String,
    @SerializedName("back_shiny_female")
    val backShinyFemale: Any?,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any?,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any?,
    val other: OtherResponse,
    val versions: VersionsResponse,
)

data class OtherResponse(
    @SerializedName("dream_world")
    val dreamWorld: DreamWorldResponse,
    val home: HomeResponse,
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkResponse,
    val showdown: ShowdownResponse,
)

data class DreamWorldResponse(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any?,
)

data class HomeResponse(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any?,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class OfficialArtworkResponse(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_shiny")
    val frontShiny: String,
)

data class ShowdownResponse(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_female")
    val backFemale: Any?,
    @SerializedName("back_shiny")
    val backShiny: String,
    @SerializedName("back_shiny_female")
    val backShinyFemale: Any?,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any?,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class VersionsResponse(
    @SerializedName("generation-i")
    val generationI: GenerationIResponse,
    @SerializedName("generation-ii")
    val generationIi: GenerationIiResponse,
    @SerializedName("generation-iii")
    val generationIii: GenerationIiiResponse,
    @SerializedName("generation-iv")
    val generationIv: GenerationIvResponse,
    @SerializedName("generation-v")
    val generationV: GenerationVResponse,
    @SerializedName("generation-vi")
    val generationVi: GenerationViResponse,
    @SerializedName("generation-vii")
    val generationVii: GenerationViiResponse,
    @SerializedName("generation-viii")
    val generationViii: GenerationViiiResponse,
)

data class GenerationIResponse(
    @SerializedName("red-blue")
    val redBlue: RedBlueResponse,
    val yellow: YellowResponse,
)

data class RedBlueResponse(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_gray")
    val backGray: String,
    @SerializedName("back_transparent")
    val backTransparent: String,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_gray")
    val frontGray: String,
    @SerializedName("front_transparent")
    val frontTransparent: String,
)

data class YellowResponse(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_gray")
    val backGray: String,
    @SerializedName("back_transparent")
    val backTransparent: String,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_gray")
    val frontGray: String,
    @SerializedName("front_transparent")
    val frontTransparent: String,
)

data class GenerationIiResponse(
    val crystal: CrystalResponse,
    val gold: GoldResponse,
    val silver: SilverResponse,
)

data class CrystalResponse(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_shiny")
    val backShiny: String,
    @SerializedName("back_shiny_transparent")
    val backShinyTransparent: String,
    @SerializedName("back_transparent")
    val backTransparent: String,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_shiny_transparent")
    val frontShinyTransparent: String,
    @SerializedName("front_transparent")
    val frontTransparent: String,
)

data class GoldResponse(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_shiny")
    val backShiny: String,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_transparent")
    val frontTransparent: String,
)

data class SilverResponse(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_shiny")
    val backShiny: String,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_transparent")
    val frontTransparent: String,
)

data class GenerationIiiResponse(
    val emerald: EmeraldResponse,
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreenResponse,
    @SerializedName("ruby-sapphire")
    val rubySapphire: RubySapphireResponse,
)

data class EmeraldResponse(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_shiny")
    val frontShiny: String,
)

data class FireredLeafgreenResponse(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_shiny")
    val backShiny: String,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_shiny")
    val frontShiny: String,
)

data class RubySapphireResponse(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_shiny")
    val backShiny: String,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_shiny")
    val frontShiny: String,
)

data class GenerationIvResponse(
    @SerializedName("diamond-pearl")
    val diamondPearl: DiamondPearlResponse,
    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: HeartgoldSoulsilverResponse,
    val platinum: PlatinumResponse,
)

data class DiamondPearlResponse(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_female")
    val backFemale: Any?,
    @SerializedName("back_shiny")
    val backShiny: String,
    @SerializedName("back_shiny_female")
    val backShinyFemale: Any?,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any?,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class HeartgoldSoulsilverResponse(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_female")
    val backFemale: Any?,
    @SerializedName("back_shiny")
    val backShiny: String,
    @SerializedName("back_shiny_female")
    val backShinyFemale: Any?,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any?,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class PlatinumResponse(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_female")
    val backFemale: Any?,
    @SerializedName("back_shiny")
    val backShiny: String,
    @SerializedName("back_shiny_female")
    val backShinyFemale: Any?,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any?,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class GenerationVResponse(
    @SerializedName("black-white")
    val blackWhite: BlackWhiteResponse,
)

data class BlackWhiteResponse(
    val animated: AnimatedResponse,
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_female")
    val backFemale: Any?,
    @SerializedName("back_shiny")
    val backShiny: String,
    @SerializedName("back_shiny_female")
    val backShinyFemale: Any?,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any?,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class AnimatedResponse(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_female")
    val backFemale: Any?,
    @SerializedName("back_shiny")
    val backShiny: String,
    @SerializedName("back_shiny_female")
    val backShinyFemale: Any?,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any?,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class GenerationViResponse(
    @SerializedName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: OmegarubyAlphasapphireResponse,
    @SerializedName("x-y")
    val xY: XYResponse,
)

data class OmegarubyAlphasapphireResponse(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any?,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class XYResponse(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any?,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class GenerationViiResponse(
    val icons: IconsResponse,
    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoonResponse,
)

data class IconsResponse(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any?,
)

data class UltraSunUltraMoonResponse(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any?,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class GenerationViiiResponse(
    val icons: Icons2Response,
)

data class Icons2Response(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any?,
)

data class StatResponse(
    @SerializedName("base_stat")
    val baseStat: Long,
    val effort: Long,
    val stat: Stat2Response
)

data class Stat2Response(
    val name: String,
    val url: String
)

data class TypeResponse(
    @SerializedName("slot")
    val slot: Long,
    @SerializedName("type")
    val type: Type2Response
)

data class Type2Response(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
