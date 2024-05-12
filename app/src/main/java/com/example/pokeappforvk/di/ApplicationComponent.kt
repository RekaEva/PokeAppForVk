package com.example.pokeappforvk.di

import com.example.pokeappforvk.data.network.NetworkModule
import com.example.pokeappforvk.presentation.MainActivity
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}