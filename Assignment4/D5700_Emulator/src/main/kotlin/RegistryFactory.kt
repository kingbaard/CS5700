package org.example

class RegistryFactory {
    fun createRegistry(type: RegistryType): Registry {
        return when (type) {
            RegistryType.TWO_BYTE -> TwoByteRegistry()
            RegistryType.ONE_BYTE -> OneByteRegistry()
            RegistryType.ONE_BIT -> OneBitRegistry()
        }
    }
}