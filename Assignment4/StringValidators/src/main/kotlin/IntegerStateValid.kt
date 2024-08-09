import org.example.IntegerVerifier

class IntegerStateValid : IntegerState {
    override fun consumeCharacter(char: String, integerVerifier: IntegerVerifier) {
        if (char in "0123456789") {
            integerVerifier.state = IntegerStateValid()
        } else {
            integerVerifier.state = IntegerStateInvalid()
        }
    }
}