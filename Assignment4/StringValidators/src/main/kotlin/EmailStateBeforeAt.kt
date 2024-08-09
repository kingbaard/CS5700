class EmailStateBeforeAt : EmailState {
    override fun consumeCharacter(char: String, emailAddressVerifier: EmailAddressVerifier) {
        if (char == "@") {
            emailAddressVerifier.state = EmailStateAfterAt()
        } else {
            emailAddressVerifier.state = EmailStateBeforeAt()
        }
    }
}