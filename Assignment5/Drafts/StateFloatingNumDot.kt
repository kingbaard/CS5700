class StateFloatingNumNoDot : State() {
    override fun consumeCharacter(char: String, floatingPointVerifier: FloatingPointVerifier) {
        if (char in "0123456789") {
            floatingPointVerifier.state = StateFloatingValid()
        } else {
            floatingPointVerifier.state = Invalid()
        }
    }
}