class FloatingPointVerifier {
    lateinit var state: FloatingPointState

    fun verify(string: String): Boolean {
        state = FloatingPointStateFirstDigit()
        string.chunked(1).forEach {
            println("current state: $state")
            state.consumeCharacter(it, this)
            println("Char is $it, state changed to $state \n")
        }
        return state is FloatingPointStateValid
    }
} 