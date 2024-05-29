package com.devgalan.tucofradia2.core

interface StorageData {
    fun saveString(key:String, value:String)
    fun getString(key:String):String?
    fun encryptAndSaveString(key:String, value:String)
    fun decryptAndGetString(key:String):String?
    fun saveInt(key:String, value:Int)
    fun getInt(key:String):Int
    fun saveBoolean(key:String, value:Boolean)
    fun getBoolean(key:String):Boolean
    fun remove(key:String)
    fun clear()
}