class BinaryStateMiddleDigit : BinaryState {
    override fun consumeCharacter(char: String, binaryVerifier: BinaryVerifier) {
        if (char == "1" || char == "0") {
            binaryVerifier.state = BinaryStateMiddleDigit()
        } else {
            binaryVerifier.state = BinaryStateInvalid()
        }
    }
}