package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class RAM(initData: UByteArray?) : MemoryDevice(initData) {
    override val isWritable: Boolean = true
}