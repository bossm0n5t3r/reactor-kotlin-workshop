package reactor

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class Part01FluxTest {
    private val sut = Part01Flux()

// ========================================================================================

    @Test
    fun empty() {
        val flux: Flux<String> = sut.emptyFlux()

        StepVerifier.create(flux)
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun fromValues() {
        val flux: Flux<String> = sut.fooBarFluxFromValues()
        StepVerifier.create(flux)
            .expectNext("foo", "bar")
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun fromList() {
        val flux: Flux<String> = sut.fooBarFluxFromList()
        StepVerifier.create(flux)
            .expectNext("foo", "bar")
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun error() {
        val flux: Flux<String> = sut.errorFlux()
        StepVerifier.create(flux)
            .verifyError(IllegalStateException::class.java)
    }

// ========================================================================================

    @Test
    fun countEach100ms() {
        val flux: Flux<Long> = sut.counter()
        StepVerifier.create(flux)
            .expectNext(0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)
            .verifyComplete()
    }
}
