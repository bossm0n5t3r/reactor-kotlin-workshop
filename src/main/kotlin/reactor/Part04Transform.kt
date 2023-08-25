package reactor

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User

/**
 * Learn how to transform values.
 */
class Part04Transform {

// ========================================================================================

    fun capitalizeOne(mono: Mono<User>): Mono<User> {
        TODO("Capitalize the user username, firstname and lastname")
    }

// ========================================================================================

    fun capitalizeMany(flux: Flux<User>): Flux<User> {
        TODO("Capitalize the users username, firstName and lastName")
    }

// ========================================================================================

    fun asyncCapitalizeMany(flux: Flux<User>): Flux<User> {
        TODO("Capitalize the users username, firstName and lastName using #asyncCapitalizeUser")
    }

    fun asyncCapitalizeUser(user: User): Mono<User> {
        return Mono.just(
            User(
                user.username.uppercase(),
                user.firstname?.uppercase(),
                user.lastname?.uppercase(),
            ),
        )
    }
}
