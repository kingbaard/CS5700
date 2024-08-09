class StateFloatingNumNoDot : State() {
    override fun consumeCharacter(char: String, floatingPointVerifier: FloatingPointVerifier) {
        if (char in "0123456789") {
            floatingPointVerifier.state = StateFloatingNumNoDot()
        } else if (char == ".") {
            floatingPointVerifier.state = StateFloatingNumDot()
        } else {
            floatingPointVerifier.state = Invalid()
        }
    }
}