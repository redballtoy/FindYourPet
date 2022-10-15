package com.example.redballtoy.findyourpet.common.domain.model.organization

import android.location.Address

data class Contact(
    val email: String,
    val phone: String,
    val address: Address
)
