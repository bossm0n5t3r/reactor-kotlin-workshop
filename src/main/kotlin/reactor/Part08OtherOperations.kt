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
        TODO("Create a Flux of user from Flux of username, firstname and lastname.")
    }

// ========================================================================================

    fun useFastestMono(mono1: Mono<User>, mono2: Mono<User>): Mono<User> {
        TODO("Return the mono which returns its value faster")
    }

// ========================================================================================

    fun useFastestFlux(flux1: Flux<User>, flux2: Flux<User>): Flux<User> {
        TODO("Return the flux which returns the first value faster")
    }

// ========================================================================================

    fun fluxCompletion(flux: Flux<User>): Mono<Void> {
        TODO("Convert the input Flux<User> to a Mono<Void> that represents the complete signal of the flux")
    }

// ========================================================================================

    fun nullAwareUserToMono(user: User?): Mono<User> {
        TODO(
            "Return a valid Mono of user for null input " +
                "and non null input user (hint: Reactive Streams do not accept null values)",
        )
    }

// ========================================================================================

    fun emptyToSkyler(mono: Mono<User>): Mono<User> {
        TODO(
            "Return the same mono passed as input parameter, " +
                "expect that it will emit User.SKYLER when empty",
        )
    }

// ========================================================================================

    fun fluxCollection(flux: Flux<User>): Mono<List<User>> {
        TODO("Convert the input Flux<User> to a Mono<List<User>> containing list of collected flux values")
    }
}
