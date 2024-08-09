class FloatingPointStateFirstIsZero : FloatingPointState {
    override fun consumeCharacter(char: String, floatingPointVerifier: FloatingPointVerifier) {
        if (char == ".") {
            floatingPointVerifier.state = FloatingPointStateNumDot()
        } else {
            floatingPointVerifier.state = FloatingPointStateInvalid()
        }
    }
}