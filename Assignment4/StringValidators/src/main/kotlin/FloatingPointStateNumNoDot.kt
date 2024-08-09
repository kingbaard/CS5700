class FloatingPointStateNumNoDot : FloatingPointState {
    override fun consumeCharacter(char: String, floatingPointVerifier: FloatingPointVerifier) {
        if (char in "0123456789") {
            floatingPointVerifier.state = FloatingPointStateNumNoDot()
        } else if (char == ".") {
            floatingPointVerifier.state = FloatingPointStateNumDot() //numdot
        } else {
            floatingPointVerifier.state = FloatingPointStateInvalid()
        }
    }
}