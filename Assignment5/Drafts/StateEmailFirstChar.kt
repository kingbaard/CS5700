class StateEmailFirstChar : State {
    override fun consumeCharacter(char: String, emailVerifier: EmailVerifier) {
        if (char == "@") {
            binaryVerifier.state = StateEmailInvalid()
        } else {
            binaryVerifier.state = StateEmailBeforeAt()
        }
    }
}