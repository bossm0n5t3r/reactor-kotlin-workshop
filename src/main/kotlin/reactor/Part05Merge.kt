package reactor

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User

/**
 * Learn how to merge flux.
 */
class Part05Merge {

// ========================================================================================

    fun mergeFluxWithInterleave(flux1: Flux<User>, flux2: Flux<User>): Flux<User> {
        return flux1.mergeWith(flux2)
    }

// ========================================================================================

    fun mergeFluxWithNoInterleave(flux1: Flux<User>, flux2: Flux<User>): Flux<User> {
        return flux1.concatWith(flux2)
    }

// ========================================================================================

    fun createFluxFromMultipleMono(mono1: Mono<User>, mono2: Mono<User>): Flux<User> {
        return Flux.concat(mono1, mono2)
    }
}
