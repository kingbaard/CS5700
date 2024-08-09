class StatePasswordNoSpecialNoCap : State {
    override fun consumeCharacter(char: String, emailVerifier: EmailVerifier) {
        if (char in "ABCDEFGHIJKLMNOPQRSTUVWXYZ") {
            binaryVerifier.state = StatePasswordValid()
        }
    }
}