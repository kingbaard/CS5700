package org.example

class EmailAddressVerifier {
    lateinit var state: State

    fun verify(string: String): Boolean {
        state = StateEmailFirstChar()
        string.chunk(1).forEach {
            state.consumeCharacter(it, this)
        }

        return state is Valid
    }
} 