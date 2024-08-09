class StateIntegerFirstDigit : State {
    override fun consumeCharacter(char: String, binaryVerifier: BinaryVerifier) {
        if (char == "1") {
            binaryVerifier.state = StateBinaryDigit()
        } else {
            binaryVerifier.state = Invalid()
        }
    }
}