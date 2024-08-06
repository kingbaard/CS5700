package org.example

@OptIn(ExperimentalUnsignedTypes::class)
abstract class MemoryDevice(initData: UByteArray?) {
    private var _data: UByteArray = initData?.copyOf() ?: UByteArray(4000) { 0.toUByte() }

    var data: UByteArray
        get() = _data
        set(value) {
            if (isWritable) {
                _data = value
            } else {
                throw IllegalAccessException("Cannot modify data, the device is not writable.")
            }
        }

    abstract val isWritable: Boolean
}