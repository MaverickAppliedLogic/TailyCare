package com.example.feedm.petsFeature.domain

import com.example.feedm.core.data.database.entities.toDataBase
import com.example.feedm.petsFeature.data.PetsRepository
import com.example.feedm.core.domain.model.PetModel
import javax.inject.Inject

class GetPets @Inject constructor(private val repository: PetsRepository){
    suspend operator fun invoke(): List<PetModel>{
        var pets = repository.getAllPetsFromDB()
        return if (pets.isNotEmpty()){
            repository.insertPetsToStorage(pets)
            pets
        }
        else{
            pets = repository.getAllPetsFromStorage()
            repository.insertPetsToDB(pets.map { it.toDataBase() })
            //Se vuelve a recoger de la DB para que se les asignen ID's
            pets = repository.getAllPetsFromDB()
            pets
        }

    }
}