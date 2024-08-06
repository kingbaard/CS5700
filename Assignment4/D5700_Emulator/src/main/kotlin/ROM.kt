package org.example

@OptIn(ExperimentalUnsignedTypes::class)
class ROM(initData: UByteArray?) : MemoryDevice(initData) {
    override val isWritable: Boolean = false
}