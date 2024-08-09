class StateEmailValid : State {
    override fun consumeCharacter(char: String, emailVerifier: EmailVerifier) {
        if (char == "@" || char == ".") {
            binaryVerifier.state = StateEmailInvalid()
        }
    }
}