package org.example

sealed class RegistryDataType {
    data class Integer(val value: Int): RegistryDataType()
    @OptIn(ExperimentalUnsignedTypes::class)
    data class UByteArray(val value: kotlin.UByteArray) : RegistryDataType()
}

@OptIn(ExperimentalUnsignedTypes::class)
abstract class Registry {
    abstract val size: Int
    abstract var data: RegistryDataType

    fun getValueAsInt(): Int {
        return when (val data = this.data) {
            is RegistryDataType.Integer -> data.value
            is RegistryDataType.UByteArray -> data.value.fold(0) { acc, byte -> (acc shl 8) or byte.toInt() }
        }
    }

    open fun setValue(value: Int) {
//        println("setting value to $value")
        this.data = RegistryDataType.Integer(value)
    }

    open fun setValue(value: UByteArray) {
        this.data = RegistryDataType.UByteArray(value)
    }

    fun setValue(byteValue: UByte) {
        val newUByteArray: UByteArray = ubyteArrayOf(byteValue)
        this.data = RegistryDataType.UByteArray(newUByteArray)
    }
}