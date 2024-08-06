package org.example

class RegistryFactory {
    fun createRegistry(type: RegisterType): Register {
        return when (type) {
            RegisterType.TWO_BYTE -> TwoByteRegistry()
            RegisterType.ONE_BYTE -> OneByteRegistry()
            RegisterType.ONE_BIT -> OneBitRegistry()
        }
    }
}