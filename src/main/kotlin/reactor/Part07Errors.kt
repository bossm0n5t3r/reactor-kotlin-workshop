package reactor

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User

class Part07Errors {

// ========================================================================================

    fun betterCallSaulForBogusMono(mono: Mono<User>): Mono<User> {
        TODO(
            "Return a Mono<User> containing User.SAUL " +
                "when an error occurs in the input Mono, " +
                "else do not change the input Mono.",
        )
    }

// ========================================================================================

    fun betterCallSaulAndJesseForBogusFlux(flux: Flux<User>): Flux<User> {
        TODO(
            "Return a Flux<User> containing User.SAUL and User.JESSE " +
                "when an error occurs in the input Flux, " +
                "else do not change the input Flux.",
        )
    }

// ========================================================================================

    fun capitalizeMany(flux: Flux<User>): Flux<User> {
        TODO(
            "Implement a method that capitalizes each user of the incoming flux " +
                "using the #capitalizeUser method and " +
                "emits an error containing a GetOutOfHereException error",
        )
    }

    @Throws(GetOutOfHereException::class)
    fun capitalizeUser(user: User): User {
        if (user == User.SAUL) {
            throw GetOutOfHereException()
        }
        return User(user.username, user.firstname, user.lastname)
    }

    class GetOutOfHereException : Exception()
}
