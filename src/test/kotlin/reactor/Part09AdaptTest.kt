package reactor

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User
import reactor.repository.ReactiveRepository
import reactor.repository.ReactiveUserRepository
import reactor.test.StepVerifier
import java.util.concurrent.CompletableFuture

class Part09AdaptTest {
    private val sut = Part09Adapt()
    private val repository: ReactiveRepository<User> = ReactiveUserRepository()

// ========================================================================================

    @Test
    fun adaptToFlowable() {
        val flux: Flux<User> = repository.findAll()
        val flowable: Flowable<User> = sut.fromFluxToFlowable(flux)
        StepVerifier.create(sut.fromFlowableToFlux(flowable))
            .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun adaptToObservable() {
        val flux: Flux<User> = repository.findAll()
        val observable: Observable<User> = sut.fromFluxToObservable(flux)
        StepVerifier.create(sut.fromObservableToFlux(observable))
            .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun adaptToSingle() {
        val mono: Mono<User> = repository.findFirst()
        val single: Single<User> = sut.fromMonoToSingle(mono)
        StepVerifier.create(sut.fromSingleToMono(single))
            .expectNext(User.SKYLER)
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun adaptToCompletableFuture() {
        val mono: Mono<User> = repository.findFirst()
        val future: CompletableFuture<User> = sut.fromMonoToCompletableFuture(mono)
        StepVerifier.create(sut.fromCompletableFutureToMono(future))
            .expectNext(User.SKYLER)
            .verifyComplete()
    }
}
