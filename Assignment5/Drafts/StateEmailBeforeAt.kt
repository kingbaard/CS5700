class StateEmailBeforeAt : State {
    override fun consumeCharacter(char: String, emailVerifier: EmailVerifier) {
        if (char == "@") {
            binaryVerifier.state = StateEmailBeforeAt()
        } else {
            binaryVerifier.state = StateEmailBeforeAt()
        }
    }
}