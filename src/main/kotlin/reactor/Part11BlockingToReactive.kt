package reactor

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.domain.User
import reactor.kotlin.core.publisher.toFlux
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
        return Flux
            .defer { repository.findAll().toFlux() }
            .subscribeOn(Schedulers.boundedElastic())
    }

// ========================================================================================

    fun fluxToBlockingRepository(flux: Flux<User>, repository: BlockingRepository<User>): Mono<Void> {
        return flux
            .publishOn(Schedulers.boundedElastic())
            .doOnNext { repository.save(it) }
            .then()
    }
}
