package reactor

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User
import java.time.Duration

class Part03StepVerifierTest {
    private val sut = Part03StepVerifier()

// ========================================================================================

    @Test
    fun expectElementsThenComplete() {
        sut.expectFooBarComplete(Flux.just("foo", "bar"))
    }

// ========================================================================================

    @Test
    fun expect2ElementsThenError() {
        sut.expectFooBarError(
            Flux.just("foo", "bar")
                .concatWith(Mono.error(RuntimeException())),
        )
    }

// ========================================================================================

    @Test
    fun expectElementsWithThenComplete() {
        sut.expectSkylerJesseComplete(
            Flux.just(
                User("swhite", null, null),
                User("jpinkman", null, null),
            ),
        )
    }

// ========================================================================================

    @Test
    fun count() {
        sut.expect10Elements(Flux.interval(Duration.ofSeconds(1)).take(10))
    }

// ========================================================================================

    @Test
    fun countWithVirtualTime() {
        sut.expect3600Elements {
            Flux.interval(Duration.ofSeconds(1)).take(3600)
        }
    }
}
