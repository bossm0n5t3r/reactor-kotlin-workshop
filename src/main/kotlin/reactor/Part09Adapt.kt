package reactor

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User
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
        TODO("Adapt Flux to RxJava Flowable")
    }

    fun fromFlowableToFlux(flowable: Flowable<User>): Flux<User> {
        TODO("Adapt RxJava Flowable to Flux")
    }

// ========================================================================================

    fun fromFluxToObservable(flux: Flux<User>): Observable<User> {
        TODO("Adapt Flux to RxJava Observable")
    }

    fun fromObservableToFlux(observable: Observable<User>): Flux<User> {
        TODO("Adapt RxJava Observable to Flux")
    }

// ========================================================================================

    fun fromMonoToSingle(mono: Mono<User>): Single<User> {
        TODO("Adapt Mono to RxJava Single")
    }

    fun fromSingleToMono(single: Single<User>): Mono<User> {
        TODO("Adapt RxJava Single to Mono")
    }

// ========================================================================================

    fun fromMonoToCompletableFuture(mono: Mono<User>): CompletableFuture<User> {
        TODO("Adapt Mono to Java 8+ CompletableFuture")
    }

    fun fromCompletableFutureToMono(future: CompletableFuture<User>): Mono<User> {
        TODO("Adapt Java 8+ CompletableFuture to Mono")
    }
}
