package com.example.feedm.petsFeature.utils

import android.icu.util.Calendar
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import javax.inject.Inject


class MealModelBuilder @Inject constructor(){

    operator fun invoke(
        petId: Int, foodId: Int, ration: Float, hour: Int, min: Int, mealCalories: Double
    ): MealModel {
        return MealModel(
            petId = petId,
            foodId = foodId,
            mealTime = timeToLong(hour, min),
            ration = ration,
            mealCalories =  mealCalories * ration / 100)
    }

    private fun timeToLong(hour: Int,min: Int): Long{
        val time = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, min)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return time.timeInMillis
    }
}