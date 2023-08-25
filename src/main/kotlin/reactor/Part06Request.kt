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
        TODO("Create a StepVerifier that initially requests all values and expect 4 values to be received")
    }

// ========================================================================================

    fun requestOneExpectSkylerThenRequestOneExpectJesse(flux: Flux<User>): StepVerifier {
        TODO("Create a StepVerifier that initially requests 1 value and expects User.SKYLER then requests another value and expects User.JESSE then stops verifying by cancelling the source")
    }

// ========================================================================================

    fun fluxWithLog(): Flux<User> {
        TODO("Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals")
    }

// ========================================================================================

    fun fluxWithDoOnPrintln(): Flux<User> {
        TODO("Return a Flux with all users stored in the repository that prints \"Starring:\" at first, \"firstname lastname\" for all values and \"The end!\" on complete")
    }
}
