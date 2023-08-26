package reactor

import reactor.core.publisher.Flux
import reactor.domain.User
import reactor.kotlin.test.test
import reactor.kotlin.test.verifyError
import reactor.test.StepVerifier
import java.time.Duration
import java.util.function.Supplier

/**
 * Learn how to use StepVerifier to test Mono, Flux or any other kind of Reactive Streams Publisher.
 *
 * @see <a href="https://projectreactor.io/docs/test/release/api/reactor/test/StepVerifier.html">
 *     StepVerifier Javadoc
 *     </a>
 */
class Part03StepVerifier {

// ========================================================================================

    fun expectFooBarComplete(flux: Flux<String>) {
        flux.test()
            .expectNext("foo", "bar")
            .verifyComplete()
    }

// ========================================================================================

    fun expectFooBarError(flux: Flux<String>) {
        flux.test()
            .expectNext("foo", "bar")
            .verifyError(RuntimeException::class)
    }

// ========================================================================================

    fun expectSkylerJesseComplete(flux: Flux<User>) {
        flux.test()
            .expectNext(
                User("swhite", null, null),
                User("jpinkman", null, null),
            )
            .verifyComplete()
    }

// ========================================================================================

    fun expect10Elements(flux: Flux<Long>) {
        flux.test()
            .expectNextCount(10)
            .verifyComplete()
    }

// ========================================================================================

    fun expect3600Elements(supplier: Supplier<Flux<Long>>) {
        StepVerifier.withVirtualTime(supplier)
            .thenAwait(Duration.ofHours(1))
            .expectNextCount(3600)
            .verifyComplete()
    }
}
