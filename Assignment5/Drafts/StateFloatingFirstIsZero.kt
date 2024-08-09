class StateFloatingNumNoDot : State() {
    override fun consumeCharacter(char: String, floatingPointVerifier: FloatingPointVerifier) {
        if (char == ".") {
            floatingPointVerifier.state = StateFloatingNumDot()
        } else {
            floatingPointVerifier.state = Invalid()
        }
    }
}