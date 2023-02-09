package com.example.safefood.firebase.di

import com.example.safefood.firebase.data.Authentication
import com.example.safefood.firebase.data.AuthenticationImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesRepositoryImp(firebaseAuth: FirebaseAuth):Authentication{
        return AuthenticationImpl(firebaseAuth)
    }
}