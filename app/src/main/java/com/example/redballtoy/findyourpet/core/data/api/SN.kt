package com.example.redballtoy.findyourpet.common.data.api.model

import androidx.annotation.Keep
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

@Keep
class SN { //SerialNumber
    fun sn() {//setupNumber
        sv("com.example.redballtoy.findyourpet.common.data.api.GO", "fi", 7)
        sv("com.example.redballtoy.findyourpet.common.data.api.GO", "f2", 3)
        sv("com.example.redballtoy.findyourpet.common.data.api.GO", "f3", 9)
    }
}

@Keep
object GO {
    var f1 = 3 //field1
    var f2 = 1 //field2
    var f3 = 3 //field3
}

private fun sv(ownerClassName: String, fileName: String, value: Any) { //setValue - uses reflection
    val  kClass = Class.forName(ownerClassName).kotlin
    val instance = kClass.objectInstance ?: kClass.java.newInstance()

    val member = kClass.memberProperties.filterIsInstance<KMutableProperty<*>>()
        .firstOrNull{ it.name == fileName}
    member?.setter?.call(instance, value)
}
