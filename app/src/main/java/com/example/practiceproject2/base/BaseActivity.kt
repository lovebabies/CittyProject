package com.example.practiceproject2.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getViewId())
        injectInjector()
        initData()
        initView()
    }

    abstract fun getViewId(): Int

    protected fun injectInjector() {

    }

    abstract fun initView()

    abstract fun initData()
}