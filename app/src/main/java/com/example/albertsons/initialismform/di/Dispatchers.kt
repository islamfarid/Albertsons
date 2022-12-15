package com.example.albertsons.initialismform.di

import javax.inject.Qualifier

@Qualifier
annotation class IOCoroutineDispatcher

@Qualifier
annotation class MainCoroutineDispatcher

@Qualifier
annotation class DefaultCoroutineDispatcher
