package reactor

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User
import reactor.repository.BlockingRepository

/**
 * Learn how to call blocking code from Reactive one with adapted concurrency strategy for
 * blocking code that produces or receives data.
 *
 * For those who know RxJava:
 *  - RxJava subscribeOn = Reactor subscribeOn
 *  - RxJava observeOn = Reactor publishOn
 *  - RxJava Schedulers.io <==> Reactor Schedulers.elastic
 *
 * @see Flux#subscribeOn(Scheduler)
 * @see Flux#publishOn(Scheduler)
 * @see Schedulers
 */
class Part11BlockingToReactive {

// ========================================================================================

    fun blockingRepositoryToFlux(repository: BlockingRepository<User>): Flux<User> {
        TODO(
            "Create a Flux " +
                "for reading all users from the blocking repository " +
                "deferred until the flux is subscribed, " +
                "and run it with a bounded elastic scheduler",
        )
    }

// ========================================================================================

    fun fluxToBlockingRepository(flux: Flux<User>, repository: BlockingRepository<User>): Mono<Void> {
        TODO(
            "Insert users contained in the Flux parameter in the blocking repository " +
                "using a bounded elastic scheduler " +
                "and return a Mono<Void> that signal the end of the operation",
        )
    }
}
