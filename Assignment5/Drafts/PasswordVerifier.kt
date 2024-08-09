package org.example

class EmailAddressVerifier {
    lateinit var state: State

    fun verify(string: String): Boolean {
        if (string.size < 8 || string.last in "!@#$%^&*()") { return false }
        
        state = StatePasswordNoSpecialNoCap()
        string.chunk(1).forEach {
            state.consumeCharacter(it, this)
        }

        return state is Valid
    }
} 