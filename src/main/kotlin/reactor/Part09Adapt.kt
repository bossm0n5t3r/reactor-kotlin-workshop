package reactor

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.CompletableFuture

/**
 * Learn how to adapt from/to RxJava 3 Observable/Single/Flowable and Java 8+ CompletableFuture.
 *
 * Mono and Flux already implements Reactive Streams interfaces so they are natively
 * Reactive Streams compliant + there are {@link Mono#from(Publisher)} and {@link Flux#from(Publisher)}
 * factory methods.
 *
 * For RxJava 3, you should not use Reactor Adapter but only RxJava 3 and Reactor Core.
 */
class Part09Adapt {

// ========================================================================================

    fun fromFluxToFlowable(flux: Flux<User>): Flowable<User> {
        return Flowable.fromPublisher(flux)
    }

    fun fromFlowableToFlux(flowable: Flowable<User>): Flux<User> {
        return Flux.from(flowable)
    }

// ========================================================================================

    fun fromFluxToObservable(flux: Flux<User>): Observable<User> {
        return Observable.fromPublisher(flux)
    }

    fun fromObservableToFlux(observable: Observable<User>): Flux<User> {
        return Flux.from(Flowable.fromObservable(observable, BackpressureStrategy.BUFFER))
    }

// ========================================================================================

    fun fromMonoToSingle(mono: Mono<User>): Single<User> {
        return Single.fromPublisher(mono)
    }

    fun fromSingleToMono(single: Single<User>): Mono<User> {
        return Mono.from(Flowable.fromSingle(single))
    }

// ========================================================================================

    fun fromMonoToCompletableFuture(mono: Mono<User>): CompletableFuture<User> {
        return mono.toFuture()
    }

    fun fromCompletableFutureToMono(future: CompletableFuture<User>): Mono<User> {
        return future.toMono()
    }
}
