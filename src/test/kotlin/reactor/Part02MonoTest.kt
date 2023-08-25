package reactor

import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.Duration

class Part02MonoTest {
    private val sut = Part02Mono()

// ========================================================================================

    @Test
    fun empty() {
        val mono: Mono<String> = sut.emptyMono()
        StepVerifier.create(mono)
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun noSignal() {
        val mono: Mono<String> = sut.monoWithNoSignal()
        StepVerifier
            .create(mono)
            .expectSubscription()
            .expectTimeout(Duration.ofSeconds(1))
            .verify()
    }

// ========================================================================================

    @Test
    fun fromValue() {
        val mono: Mono<String> = sut.fooMono()
        StepVerifier.create(mono)
            .expectNext("foo")
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun error() {
        val mono: Mono<String> = sut.errorMono()
        StepVerifier.create(mono)
            .verifyError(IllegalStateException::class.java)
    }
}
