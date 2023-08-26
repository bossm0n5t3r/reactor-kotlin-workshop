package reactor

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.domain.User
import reactor.repository.ReactiveUserRepository
import reactor.test.StepVerifier
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class Part06RequestTest {
    private val sut = Part06Request()
    private val repository = ReactiveUserRepository()

    private val originalConsole = System.out

    private val logConsole: ByteArrayOutputStream = ByteArrayOutputStream()

    @AfterEach
    fun afterEach() {
        originalConsole.println(logConsole.toString())
        System.setOut(originalConsole)
    }

// ========================================================================================

    @Test
    fun requestAll() {
        val flux: Flux<User> = repository.findAll()
        val verifier: StepVerifier = sut.requestAllExpectFour(flux)
        verifier.verify()
    }

// ========================================================================================

    @Test
    fun requestOneByOne() {
        val flux: Flux<User> = repository.findAll()
        val verifier: StepVerifier = sut.requestOneExpectSkylerThenRequestOneExpectJesse(flux)
        verifier.verify()
    }

// ========================================================================================

    @Test
    fun experimentWithLog() {
        System.setOut(PrintStream(logConsole))

        val flux: Flux<User> = sut.fluxWithLog()

        StepVerifier.create(flux, 0)
            .thenRequest(1)
            .expectNextMatches { _ -> true }
            .thenRequest(1)
            .expectNextMatches { _ -> true }
            .thenRequest(2)
            .expectNextMatches { _ -> true }
            .expectNextMatches { _ -> true }
            .verifyComplete()

        val log: List<String> = logConsole.toString()
            .split(System.lineSeparator())
            .filter { it.contains("INFO") }
            .map {
                it
                    .replace("[ INFO] (", "")
                    .substringAfter(") ")
            }

        assertThat(log)
            .containsExactly(
                "onSubscribe(FluxZip.ZipCoordinator)",
                "request(1)",
                "onNext(User(username=swhite, firstname=Skyler, lastname=White))",
                "request(1)",
                "onNext(User(username=jpinkman, firstname=Jesse, lastname=Pinkman))",
                "request(2)",
                "onNext(User(username=wwhite, firstname=Walter, lastname=White))",
                "onNext(User(username=sgoodman, firstname=Saul, lastname=Goodman))",
                "onComplete()",
            )
    }

// ========================================================================================

    @Test
    fun experimentWithDoOn() {
        val flux: Flux<User> = sut.fluxWithDoOnPrintln()

        // setting up the logConsole here should ensure we only capture console logs from the Flux
        System.setOut(PrintStream(logConsole))

        StepVerifier.create(flux)
            .expectNextCount(4)
            .verifyComplete()

        val log = logConsole.toString().split(System.lineSeparator())

        assertThat(log)
            .containsExactly("Starring:", "Skyler White", "Jesse Pinkman", "Walter White", "Saul Goodman", "The end!")
    }
}
