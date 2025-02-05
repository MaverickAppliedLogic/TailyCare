package com.example.feedm.petsFeature.domain.petsUseCases

import com.example.feedm.petsFeature.data.PetsRepository
import com.example.feedm.core.data.database.entities.toDataBase
import com.example.feedm.petsFeature.domain.model.PetModel
import javax.inject.Inject

class EditPetUseCase @Inject constructor(private val repository: PetsRepository) {
    suspend operator fun invoke(petModel: PetModel) {
        repository.updatePet(petModel.toDataBase())
    }
}