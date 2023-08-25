package reactor

import reactor.core.publisher.Flux

/**
 * Learn how to create Flux instances.
 *
 * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html">Flux Javadoc</a>
 */
class Part01Flux {

// ========================================================================================

    fun emptyFlux(): Flux<String> {
        TODO("Return an empty Flux")
    }

// ========================================================================================

    fun fooBarFluxFromValues(): Flux<String> {
        TODO("Return a Flux that contains 2 values \"foo\" and \"bar\" without using an array or a collection")
    }

// ========================================================================================

    fun fooBarFluxFromList(): Flux<String> {
        TODO("Create a Flux from a List that contains 2 values \"foo\" and \"bar\"")
    }

// ========================================================================================

    fun errorFlux(): Flux<String> {
        TODO("Create a Flux that emits an IllegalStateException")
    }

// ========================================================================================

    fun counter(): Flux<Long> {
        TODO("Create a Flux that emits increasing values from 0 to 9 each 100ms")
    }
}
