package com.example.pokeappforvk.features.pokemondetails.domain.models

import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("official-artwork") val officialartwork: OfficialArtwork,
)