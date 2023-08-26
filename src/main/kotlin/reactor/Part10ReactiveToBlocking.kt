package reactor

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User

/**
 * Learn how to turn Reactive API to blocking one.
 */
class Part10ReactiveToBlocking {

// ========================================================================================

    fun monoToValue(mono: Mono<User>): User? {
        TODO("Return the user contained in that Mono")
    }

// ========================================================================================

    fun fluxToValues(flux: Flux<User>): Iterable<User> {
        TODO("Return the users contained in that Flux")
    }
}
