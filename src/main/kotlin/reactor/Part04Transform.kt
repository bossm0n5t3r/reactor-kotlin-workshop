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
        return mono.map { user ->
            User(
                username = user.username.uppercase(),
                firstname = user.firstname?.uppercase(),
                lastname = user.lastname?.uppercase(),
            )
        }
    }

// ========================================================================================

    fun capitalizeMany(flux: Flux<User>): Flux<User> {
        return flux.map { user ->
            User(
                username = user.username.uppercase(),
                firstname = user.firstname?.uppercase(),
                lastname = user.lastname?.uppercase(),
            )
        }
    }

// ========================================================================================

    fun asyncCapitalizeMany(flux: Flux<User>): Flux<User> {
        return flux.flatMap { user ->
            asyncCapitalizeUser(user)
        }
    }

    private fun asyncCapitalizeUser(user: User): Mono<User> {
        return Mono.just(
            User(
                user.username.uppercase(),
                user.firstname?.uppercase(),
                user.lastname?.uppercase(),
            ),
        )
    }
}
