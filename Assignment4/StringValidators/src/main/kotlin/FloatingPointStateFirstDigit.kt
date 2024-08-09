class FloatingPointStateFirstDigit : FloatingPointState {
     override fun consumeCharacter(char: String, floatingPointVerifier: FloatingPointVerifier) {
         if (char == "0") {
             floatingPointVerifier.state = FloatingPointStateFirstIsZero()
         } else if (char in "123456789") {
             floatingPointVerifier.state = FloatingPointStateNumNoDot()
         } else if (char == ".") {
             floatingPointVerifier.state = FloatingPointStateNumDot()
         } else {
             floatingPointVerifier.state = FloatingPointStateInvalid()
         }
    }

}