package reactor

import reactor.core.publisher.Flux
import java.time.Duration

/**
 * Learn how to create Flux instances.
 *
 * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html">Flux Javadoc</a>
 */
class Part01Flux {

// ========================================================================================

    fun emptyFlux(): Flux<String> {
        return Flux.empty()
    }

// ========================================================================================

    fun fooBarFluxFromValues(): Flux<String> {
        return Flux.just("foo", "bar")
    }

// ========================================================================================

    fun fooBarFluxFromList(): Flux<String> {
        return Flux.fromIterable(listOf("foo", "bar"))
    }

// ========================================================================================

    fun errorFlux(): Flux<String> {
        return Flux.error(IllegalStateException("errorFlux"))
    }

// ========================================================================================

    fun counter(): Flux<Long> {
        return Flux
            .interval(Duration.ofMillis(100L))
            .take(10L)
    }
}
