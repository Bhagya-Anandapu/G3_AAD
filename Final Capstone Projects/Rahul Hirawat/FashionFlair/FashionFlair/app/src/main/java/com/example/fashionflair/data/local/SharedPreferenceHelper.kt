package com.example.fashionflair.data.local

import android.content.Context
import android.net.Uri
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.fashionflair.domain.model.ProductItemModel
import java.lang.reflect.Type
import javax.inject.Singleton

@Singleton
class SharedPreferenceHelper(private val context: Context) {

    companion object {
        const val TABLE_NAME = "user_details"
        const val USER_NAME_KEY = "user_name"
        const val USER_EMAIL_KEY = "user_email"
        const val USER_CART_LIST = "user_cart_list"
        const val USER_PROFILE = "user_profile"

    }

    fun setEmail(email: String) {
        val sharedPref = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(USER_EMAIL_KEY, email)
        editor.apply()
    }

    fun setName(name: String) {
        val sharedPref = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(USER_NAME_KEY, name)
        editor.apply()
    }

    fun getName(): String? {
        val pref = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE)
        return pref.getString(USER_NAME_KEY, null) ?: null
    }

    fun getEmail(): String {
        val pref = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE)
        return pref.getString(USER_EMAIL_KEY, "") ?: ""
    }

    fun saveProductToCart(list: ArrayList<ProductItemModel>?) {
        val sharedPref = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(USER_CART_LIST, json)
        editor.apply()
    }

    fun getCartList(): ArrayList<ProductItemModel>? {
        val sharedPref = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPref.getString(USER_CART_LIST, "")
        val type: Type = object : TypeToken<ArrayList<ProductItemModel?>?>() {}.type
        return gson.fromJson<ArrayList<ProductItemModel>>(json, type)
    }

    fun setProfilePicture(uri: Uri) {
        val sharedPref = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(USER_PROFILE, uri.toString())
        editor.apply()
    }

    fun getProfilePicture(): Uri? {
        val sharedPref = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE)
        val uriString = sharedPref.getString(USER_PROFILE, "")
        return Uri.parse(uriString)
    }

    fun clearData() {
        val pref = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear()
        editor.apply()
    }
}