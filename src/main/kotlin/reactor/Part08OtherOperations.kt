package reactor

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User

/**
 * Learn how to use various other operators.
 */
class Part08OtherOperations {

// ========================================================================================

    fun userFluxFromStringFlux(
        usernameFlux: Flux<String>,
        firstnameFlux: Flux<String>,
        lastnameFlux: Flux<String>,
    ): Flux<User> {
        return Flux
            .zip(usernameFlux, firstnameFlux, lastnameFlux)
            .map { User(it.t1, it.t2, it.t3) }
    }

// ========================================================================================

    fun useFastestMono(mono1: Mono<User>, mono2: Mono<User>): Mono<User> {
        return Mono.firstWithValue(mono1, mono2)
    }

// ========================================================================================

    fun useFastestFlux(flux1: Flux<User>, flux2: Flux<User>): Flux<User> {
        return Flux.firstWithValue(flux1, flux2)
    }

// ========================================================================================

    fun fluxCompletion(flux: Flux<User>): Mono<Void> {
        return flux.then()
    }

// ========================================================================================

    fun nullAwareUserToMono(user: User?): Mono<User> {
        return Mono.justOrEmpty(user)
    }

// ========================================================================================

    fun emptyToSkyler(mono: Mono<User>): Mono<User> {
        return mono.defaultIfEmpty(User.SKYLER)
    }

// ========================================================================================

    fun fluxCollection(flux: Flux<User>): Mono<List<User>> {
        return flux.collectList()
    }
}
