import org.example.IntegerVerifier
import org.junit.jupiter.api.Test

class IntegerVerifierTest {

    @Test
    fun validIntegerTest() {
        val verifier = IntegerVerifier()
        assert(verifier.verify("2"))
        assert(verifier.verify("2658442"))
        assert(verifier.verify("6546123168421321654165423132165946532198789498465113216549684654854634634694968546985469469346946983"))
    }

    @Test
    fun alphabetInStringTest() {
        val verifier = IntegerVerifier()
        assert(!verifier.verify("7721a998"))
        assert(!verifier.verify("B721a998"))
        assert(!verifier.verify("7721a99C"))
    }

    @Test
    fun decimalInStringTest() {
        val verifier = IntegerVerifier()
        assert(!verifier.verify("772.1998"))
        assert(!verifier.verify(".B721a998"))
        assert(!verifier.verify("77219.9"))
    }
}