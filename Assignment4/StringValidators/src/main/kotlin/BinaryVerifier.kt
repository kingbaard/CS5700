class BinaryVerifier {
    lateinit var state: BinaryState

    fun verify(string: String): Boolean {
        state = BinaryStateFirstDigit()
        string.chunked(1).forEach {
            state.consumeCharacter(it, this)
        }
        if (string[string.length - 1] != '1') {
            state = BinaryStateInvalid()
        }
        return state is BinaryStateValid
    }
} 