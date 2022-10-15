package com.example.redballtoy.findyourpet.common.domain.model.animal.details

import android.provider.CalendarContract.Colors
import com.example.redballtoy.findyourpet.common.domain.model.organization.Organization

data class Details(
    val descriotion: String,
    val age: Age,
    val species: String,
    val breed: Breed,
    val colors: Colors,
    val gender: Gender,
    val size: Size,
    val coat: Coat,
    val healthDetails: HealthDetails,
    val habitatAdaptation: HabitatAdaptation,
    val organization: Organization
)