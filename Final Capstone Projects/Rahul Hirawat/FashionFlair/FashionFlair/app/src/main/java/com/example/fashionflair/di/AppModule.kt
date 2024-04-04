package com.example.fashionflair.di

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.fashionflair.data.local.SharedPreferenceHelper
import com.example.fashionflair.data.repository.FirebaseRepositoryImpl
import com.example.fashionflair.data.repository.LocalRepositoryImpl
import com.example.fashionflair.domain.repository.IFirebaseRepository
import com.example.fashionflair.domain.repository.ILocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabaseInstance() = Firebase.firestore


    @Provides
    @Singleton
    fun provideFirebaseRepository(db: FirebaseFirestore): IFirebaseRepository {
        return FirebaseRepositoryImpl(db)
    }

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferenceHelper {
        return SharedPreferenceHelper(context)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(sharedPreferenceHelper: SharedPreferenceHelper): ILocalRepository {
        return LocalRepositoryImpl(sharedPreferenceHelper)
    }
}