package com.teams.vidhividhan.data.di


import com.teams.vidhividhan.ui.viewModel.CommonViewModel
import com.teams.vidhividhan.ui.views.marketplace.viewModel.MarketViewModel
import com.teams.vidhividhan.ui.viewModel.PreferencesDataStoreViewModel
import com.teams.vidhividhan.ui.views.Auth.viewModel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MarketViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel{CommonViewModel()}
    single { PreferencesDataStoreViewModel(get()) }

}