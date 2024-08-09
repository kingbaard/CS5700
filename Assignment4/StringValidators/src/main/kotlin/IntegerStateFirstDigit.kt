import org.example.IntegerVerifier

class IntegerStateFirstDigit : IntegerState {
    override fun consumeCharacter(char: String, integerVerifier: IntegerVerifier) {
        if (char in "123456789") {
            integerVerifier.state = IntegerStateValid()
        } else {
            integerVerifier.state = IntegerStateInvalid()
        }
    }
}