interface EmailState {
    fun consumeCharacter(char: String, emailAddressVerifier: EmailAddressVerifier)
}