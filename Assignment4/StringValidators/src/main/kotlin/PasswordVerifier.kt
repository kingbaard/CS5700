class PasswordVerifier {
    lateinit var state: PasswordState

    fun verify(string: String): Boolean {
        if (string.length < 8 || string.last() in "!@#$%^&*()") { return false }

        state = PasswordStateNoSpecialNoCap()
        string.chunked(1).forEach {
            state.consumeCharacter(it, this)
        }

        return state is PasswordStateValid
    }
}