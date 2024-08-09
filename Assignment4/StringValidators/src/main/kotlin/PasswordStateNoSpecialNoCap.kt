class PasswordStateNoSpecialNoCap : PasswordState {
    override fun consumeCharacter(char: String, passwordVerifier: PasswordVerifier) {
        if (char in "ABCDEFGHIJKLMNOPQRSTUVWXYZ") {
            passwordVerifier.state = PasswordStateValid()
        }
    }
}