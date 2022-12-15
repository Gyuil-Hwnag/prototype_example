package com.example.prototype

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PrototypeApplication :Application(){

    // 코틀린의 전역변수 문법
    companion object {
        // 만들어져있는 SharedPreferences 를 사용해야합니다. 재생성하지 않도록 유념해주세요
        lateinit var sSharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor

        val email = "email"
        val password = "password"

    }

    override fun onCreate() {
        super.onCreate()
        // 다크모드 비활성화
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        sSharedPreferences = applicationContext.getSharedPreferences("Prototype", MODE_PRIVATE)
        editor = sSharedPreferences.edit()
    }
}