class StateIntegerFirstDigit : State {
    override fun consumeCharacter(char: String, integerVerifier: IntegerVerifier) {
        if (char in "123456789") {
            integerVerifier.state = StateIntegerValid()
        } else {
            integerVerifier.state = Invalid()
        }
    }
}