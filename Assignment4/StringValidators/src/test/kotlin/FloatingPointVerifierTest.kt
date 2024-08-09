import org.junit.jupiter.api.Test

class FloatingPointVerifierTest {

    @Test
    fun validNormalTest() {
        val verifier = FloatingPointVerifier()
        println(assert(verifier.verify("0.001")))
        assert(verifier.verify("0.001"))
        assert(verifier.verify("998.552"))
        assert(verifier.verify("1.0"))
        assert(verifier.verify("1.0"))
    }

    @Test
    fun validStartsWithDotTest() {
        val verifier = FloatingPointVerifier()
        assert(verifier.verify(".001"))
        assert(verifier.verify(".552"))
        assert(verifier.verify(".0"))
        println(assert(verifier.verify(".65465465465465")))
    }

    @Test
    fun alphabetCharinStringTest() {
        val verifier = FloatingPointVerifier()
        assert(!verifier.verify("772A.1998"))
        assert(!verifier.verify("0.B721a998"))
        assert(!verifier.verify("77219.9t"))
    }

    @Test
    fun multipleDotsTest() {
        val verifier = FloatingPointVerifier()
        assert(!verifier.verify("772.19.98"))
        assert(!verifier.verify("0.00.1"))
        assert(!verifier.verify("7..7.219.9"))
    }
}