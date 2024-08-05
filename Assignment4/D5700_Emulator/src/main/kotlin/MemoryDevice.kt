package org.example

@OptIn(ExperimentalUnsignedTypes::class)
abstract class MemoryDevice {

    val data: UByteArray = UByteArray(4000) { 0.toUByte() }
    abstract val isWritable: Boolean
}