package com.example.feedm.petsFeature.domain.petsUseCases

import com.example.feedm.petsFeature.domain.model.PetModel
import com.example.feedm.petsFeature.data.PetsRepository
import javax.inject.Inject

class GetPetByIdUseCase @Inject constructor(private val repository: PetsRepository) {

    suspend operator fun invoke(petId: Int): PetModel {
        return repository.getPetById(petId)
    }
}