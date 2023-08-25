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
        TODO("Merge flux1 and flux2 values with interleave")
    }

// ========================================================================================

    fun mergeFluxWithNoInterleave(flux1: Flux<User>, flux2: Flux<User>): Flux<User> {
        TODO("Merge flux1 and flux2 values with no interleave (flux1 values and then flux2 values)")
    }

// ========================================================================================

    fun createFluxFromMultipleMono(mono1: Mono<User>, mono2: Mono<User>): Flux<User> {
        TODO("Create a Flux containing the value of mono1 then the value of mono2")
    }
}
