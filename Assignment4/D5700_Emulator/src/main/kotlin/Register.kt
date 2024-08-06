package org.example

sealed class RegisterDataType {
    data class Integer(val value: Int): RegisterDataType()
    @OptIn(ExperimentalUnsignedTypes::class)
    data class UByteArray(val value: kotlin.UByteArray) : RegisterDataType()
}

@OptIn(ExperimentalUnsignedTypes::class)
interface Register {
    val size: Int
    var data: RegisterDataType

    fun getValueAsInt(): Int {
        return when (val data = this.data) {
            is RegisterDataType.Integer -> data.value
            is RegisterDataType.UByteArray -> data.value.fold(0) { acc, byte -> (acc shl 8) or byte.toInt() }
        }
    }

    fun setValue(value: Int) {
        this.data = RegisterDataType.Integer(value)
    }

    fun setValue(value: UByteArray) {
        this.data = RegisterDataType.UByteArray(value)
    }
}