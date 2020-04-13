package com.kryptkode.deviceid.utils.extensions

import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

/**
 * Created by kryptkode on 4/12/2020.
 */
@MainThread
inline fun <VM : ViewModel> ComponentActivity.viewModels(
    viewModelClass: KClass<VM>,
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }
    return ViewModelLazy(viewModelClass, { viewModelStore }, factoryPromise)
}