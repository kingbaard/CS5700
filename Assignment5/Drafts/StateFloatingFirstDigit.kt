class StateIntegerFirstDigit : State {
    override fun consumeCharacter(char: String, integerVerifier: IntegerVerifier) {
        if (char in "123456789") {
            integerVerifier.state = StateIntegerValid()
        } else if (char == "0") {
            integerVerifier.state = StateIntegerFirstIsZero()
        } else if (char == ".") {
            integerVerifier.state = StateFloatingNumDot()
        } else {
            integerVerifier.state = Invalid()
        }
    }
}