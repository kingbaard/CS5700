class EmailStateFirstChar : EmailState {
    override fun consumeCharacter(char: String, emailAddressVerifier: EmailAddressVerifier) {
        if (char == "@") {
            emailAddressVerifier.state = EmailStateInvalid()
        } else {
            emailAddressVerifier.state = EmailStateBeforeAt()
        }
    }
}