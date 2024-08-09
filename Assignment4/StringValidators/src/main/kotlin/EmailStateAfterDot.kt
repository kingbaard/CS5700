class EmailStateAfterDot : EmailState {
    override fun consumeCharacter(char: String, emailAddressVerifier: EmailAddressVerifier) {
        if (char == "@" || char == ".") {
            emailAddressVerifier.state = EmailStateInvalid()
        } else {
            emailAddressVerifier.state = EmailStateValid()
        }
    }
}