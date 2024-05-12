package com.example.pokeappforvk.di

import com.example.pokeappforvk.MainActivity
import com.example.pokeappforvk.data.network.NetworkModule
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}