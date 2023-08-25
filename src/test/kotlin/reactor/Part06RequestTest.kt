package reactor

import io.reactivex.rxjava3.annotations.Nullable
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

    @Nullable // null when not useful
    private var logConsole: ByteArrayOutputStream? = null

    @AfterEach
    fun afterEach() {
        if (logConsole != null) {
            originalConsole.println(logConsole.toString())
            System.setOut(originalConsole)
            logConsole = null
        }
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
        logConsole = ByteArrayOutputStream()
        System.setOut(logConsole?.let { PrintStream(it) })

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

        val log: List<String> = logConsole.toString().split(System.lineSeparator())
            .filter { it.contains("] INFO") }
            .map { it.replace(".*] INFO .* - ", "") }

        assertThat(log)
            .containsExactly(
                "onSubscribe(FluxZip.ZipCoordinator)",
                "request(1)",
                "onNext(Person{username='swhite', firstname='Skyler', lastname='White'})",
                "request(1)",
                "onNext(Person{username='jpinkman', firstname='Jesse', lastname='Pinkman'})",
                "request(2)",
                "onNext(Person{username='wwhite', firstname='Walter', lastname='White'})",
                "onNext(Person{username='sgoodman', firstname='Saul', lastname='Goodman'})",
                "onComplete()",
            )
    }

// ========================================================================================

    @Test
    fun experimentWithDoOn() {
        val flux: Flux<User> = sut.fluxWithDoOnPrintln()

        // setting up the logConsole here should ensure we only capture console logs from the Flux
        logConsole = ByteArrayOutputStream()
        System.setOut(logConsole?.let { PrintStream(it) })

        StepVerifier.create(flux)
            .expectNextCount(4)
            .verifyComplete()

        val log = logConsole.toString().split(System.lineSeparator())

        assertThat(log)
            .containsExactly("Starring:", "Skyler White", "Jesse Pinkman", "Walter White", "Saul Goodman", "The end!")
    }
}
