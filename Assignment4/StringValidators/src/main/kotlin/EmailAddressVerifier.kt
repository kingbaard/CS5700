class EmailAddressVerifier {
    lateinit var state: EmailState

    fun verify(string: String): Boolean {
        state = EmailStateFirstChar()
        string.chunked(1).forEach {
            state.consumeCharacter(it, this)
        }

        return state is EmailStateValid
    }
} 