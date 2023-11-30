package com.teams.vidhividhan.data.di

import com.teams.vidhividhan.data.network.ProfileApi
import com.teams.vidhividhan.data.source.marketRepo.MarketRepo
import com.teams.vidhividhan.data.source.profileRepo.ProfileRepo
import org.koin.dsl.module

val repoModule = module {
    single { MarketRepo(get(), get()) }
    single { ProfileRepo(get(), get()) }
}