package reactor

import reactor.core.Exceptions
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User

class Part07Errors {

// ========================================================================================

    fun betterCallSaulForBogusMono(mono: Mono<User>): Mono<User> {
        return mono
            .onErrorResume { Mono.just(User.SAUL) }
    }

// ========================================================================================

    fun betterCallSaulAndJesseForBogusFlux(flux: Flux<User>): Flux<User> {
        return flux
            .onErrorResume { Flux.just(User.SAUL, User.JESSE) }
    }

// ========================================================================================

    fun capitalizeMany(flux: Flux<User>): Flux<User> {
        return flux
            .handle { user, sink ->
                try {
                    sink.next(capitalizeUser(user))
                } catch (e: GetOutOfHereException) {
                    sink.error(Exceptions.propagate(e))
                }
            }
    }

    @Throws(GetOutOfHereException::class)
    private fun capitalizeUser(user: User): User {
        if (user == User.SAUL) {
            throw GetOutOfHereException()
        }
        return User(user.username, user.firstname, user.lastname)
    }

    class GetOutOfHereException : Exception()
}
