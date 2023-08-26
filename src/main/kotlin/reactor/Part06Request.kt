package reactor

import reactor.core.publisher.Flux
import reactor.domain.User
import reactor.repository.ReactiveUserRepository
import reactor.test.StepVerifier

/**
 * Learn how to control the demand.
 */
class Part06Request {

    private val repository = ReactiveUserRepository()

// ========================================================================================

    fun requestAllExpectFour(flux: Flux<User>): StepVerifier {
        return StepVerifier.create(flux)
            .expectNextCount(4)
            .expectComplete()
    }

// ========================================================================================

    fun requestOneExpectSkylerThenRequestOneExpectJesse(flux: Flux<User>): StepVerifier {
        return StepVerifier.create(flux)
            .thenRequest(1)
            .expectNextMatches { it == User.SKYLER }
            .thenRequest(1)
            .expectNextMatches { it == User.JESSE }
            .thenCancel()
    }

// ========================================================================================

    fun fluxWithLog(): Flux<User> {
        return repository.findAll()
            .log()
    }

// ========================================================================================

    fun fluxWithDoOnPrintln(): Flux<User> {
        return repository.findAll()
            .doFirst { println("Starring:") }
//            .doOnSubscribe { println("Starring:") }
            .doOnNext { user -> println("${user.firstname} ${user.lastname}") }
            .doOnComplete { print("The end!") }
    }
}
