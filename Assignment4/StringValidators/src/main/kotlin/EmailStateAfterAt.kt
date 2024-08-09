class EmailStateAfterAt : EmailState {
    override fun consumeCharacter(char: String, emailAddressVerifier: EmailAddressVerifier) {
        if (char == "@") {
            emailAddressVerifier.state = EmailStateInvalid()
        } else if (char == ".") {
            emailAddressVerifier.state = EmailStateAfterDot()
        }
    }
}