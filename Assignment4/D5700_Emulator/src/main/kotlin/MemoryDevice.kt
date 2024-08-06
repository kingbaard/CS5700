package org.example

@OptIn(ExperimentalUnsignedTypes::class)
abstract class MemoryDevice(initData: UByteArray?) {
    private var _data: UByteArray = initData?.copyOf() ?: UByteArray(4000) { 0.toUByte() }

    var data: UByteArray
        get(value) = _data[value]
        set(value) {
            if (isWritable) {
                _data = value
            } else {
                throw IllegalAccessException("Cannot modify data, the device is not writable.")
            }
        }

    fun read(address: Int): UByteArray {
        return data.get(address)
    }

    fun write(address: Int, data: UByte) {
        if (isWritable) {
            data.copyInto(this.data, address)
        } else {
            throw IllegalAccessException("Cannot write data, the device is not writable.")
        }
    }

    abstract val isWritable: Boolean
}