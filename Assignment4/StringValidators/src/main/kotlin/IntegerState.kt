import org.example.IntegerVerifier

interface IntegerState {
    fun consumeCharacter(char: String, integerVerifier: IntegerVerifier)
}