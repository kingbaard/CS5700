StateIntegerValid : State {
    override fun consumeCharacter(char: String, integerVerifier: IntegerVerifier) {
        if (char in "0123456789") {
            integerVerifier.state = StateIntegerValid()
        } else {
            integerVerifier.state = Invalid()
        }
    }
}