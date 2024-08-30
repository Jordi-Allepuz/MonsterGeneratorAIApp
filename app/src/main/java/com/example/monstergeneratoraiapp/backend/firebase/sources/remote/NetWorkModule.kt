package com.example.monstergeneratoraiapp.backend.firebase.sources.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {


//    // Proporciona una instancia  de FirebaseAuth para la autenticación de usuarios en Firebase Auth.
//    @Singleton
//    @Provides
//    fun provideFirebaseAuth()= FirebaseAuth.getInstance()

//    // Proporciona una instancia  de FirebaseFirestore para operaciones de base de datos en Firestore.
//    @Singleton
//    @Provides
//    fun provideFirebaseFirestore()= FirebaseFirestore.getInstance()

    // Proporciona una instancia  de FirebaseStorage para almacenar y recuperar archivos en Firebase Storage.
    @Singleton
    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = Firebase.storage

}