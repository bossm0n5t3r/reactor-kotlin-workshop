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
        return Mono.empty()
    }

// ========================================================================================

    fun monoWithNoSignal(): Mono<String> {
        return Mono.never()
    }

// ========================================================================================

    fun fooMono(): Mono<String> {
        return Mono.just("foo")
    }

// ========================================================================================

    fun errorMono(): Mono<String> {
        return Mono.error(IllegalStateException("errorMono"))
    }
}
