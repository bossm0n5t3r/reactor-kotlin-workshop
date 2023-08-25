package reactor

import reactor.core.publisher.Mono

/**
 * Learn how to create Mono instances.
 *
 * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html">Mono Javadoc</a>
 */
class Part02Mono {

// ========================================================================================

    fun emptyMono(): Mono<String> {
        TODO("Return an empty Mono")
    }

// ========================================================================================

    fun monoWithNoSignal(): Mono<String> {
        TODO("Return a Mono that never emits any signal")
    }

// ========================================================================================

    fun fooMono(): Mono<String> {
        TODO("Return a Mono that contains a \" foo \" value")
    }

// ========================================================================================

    fun errorMono(): Mono<String> {
        TODO("Create a Mono that emits an IllegalStateException")
    }
}
