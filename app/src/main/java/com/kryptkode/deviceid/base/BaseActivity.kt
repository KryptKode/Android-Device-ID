package com.kryptkode.deviceid.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.kryptkode.deviceid.BR
import com.kryptkode.deviceid.utils.extensions.viewModels
import kotlin.reflect.KClass

/**
 * Created by kryptkode on 10/23/2019.
 */
abstract class BaseActivity<D, V>(clazz: KClass<V>) :
    AppCompatActivity() where D : ViewDataBinding, V : ViewModel {

    protected lateinit var binding: D

    val viewModel: V by viewModels(clazz)

    var imei: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
    }


    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutResourceId())
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
    }

    @LayoutRes
    protected abstract fun getLayoutResourceId(): Int

}
