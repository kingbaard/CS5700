class BinaryStateFirstDigit : BinaryState {
    override fun consumeCharacter(char: String, binaryVerifier: BinaryVerifier) {
        if (char == "1") {
            binaryVerifier.state = BinaryStateMiddleDigit()
        } else {
            binaryVerifier.state = BinaryStateInvalid()
        }
    }
}