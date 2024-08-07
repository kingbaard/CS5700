package org.example

@OptIn(ExperimentalUnsignedTypes::class)
abstract class MemoryDevice(initData: UByteArray?) {
//    private var _data: UByteArray = initData?.copyOf() ?: UByteArray(4000) { 0.toUByte() }
    private var _data: UByteArray = initData?.let { initializeData(it) } ?: UByteArray(4000) { 0.toUByte() }

    //Adds to 0000 "end program" command
    fun initializeData(data: UByteArray): UByteArray {
        val additionalBytes = UByteArray(2) { 0.toInt().toUByte() }
        return data + additionalBytes
    }

    var data: UByteArray
        get() = _data
        set(value) {
            if (isWritable) {
                _data = value
            } else {
                throw IllegalAccessException("Cannot modify data, the device is not writable.")
            }
        }

    fun read(address: Int): UByte {
        return data[address]
    }

    fun write(address: Int, newData: UByte) {
        if (isWritable) {
            data[address] = newData
        } else {
            throw IllegalAccessException("Cannot write data, the device is not writable.")
        }
    }

    abstract val isWritable: Boolean
}