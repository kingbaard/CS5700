class StateEmailAfterAt : State {
    override fun consumeCharacter(char: String, emailVerifier: EmailVerifier) {
        if (char == "@") {
            binaryVerifier.state = StateEmailInvalid()
        } else (char == "."){
            binaryVerifier.state = StateEmailAfterDot()
        } else {
            binaryVerifier.state = StateEmailAfterAt()
        }
    }
}