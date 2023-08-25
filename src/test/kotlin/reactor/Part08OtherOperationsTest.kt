package reactor

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User
import reactor.repository.ReactiveRepository
import reactor.repository.ReactiveUserRepository
import reactor.test.StepVerifier
import reactor.test.publisher.PublisherProbe

class Part08OtherOperationsTest {
    private val sut = Part08OtherOperations()

    companion object {
        private val MARIE = User("mschrader", "Marie", "Schrader")
        private val MIKE = User("mehrmantraut", "Mike", "Ehrmantraut")
    }

// ========================================================================================

    @Test
    fun zipFirstNameAndLastName() {
        val usernameFlux: Flux<String> = Flux.just(User.SKYLER.username, User.JESSE.username, User.WALTER.username, User.SAUL.username)
        val firstnameFlux: Flux<String> = Flux.just(User.SKYLER.firstname, User.JESSE.firstname, User.WALTER.firstname, User.SAUL.firstname)
        val lastnameFlux: Flux<String> = Flux.just(User.SKYLER.lastname, User.JESSE.lastname, User.WALTER.lastname, User.SAUL.lastname)
        val userFlux: Flux<User> = sut.userFluxFromStringFlux(usernameFlux, firstnameFlux, lastnameFlux)
        StepVerifier.create(userFlux)
            .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun fastestMono() {
        var repository: ReactiveRepository<User> = ReactiveUserRepository(MARIE)
        var repositoryWithDelay: ReactiveRepository<User> = ReactiveUserRepository(250, MIKE)
        var mono: Mono<User> = sut.useFastestMono(repository.findFirst(), repositoryWithDelay.findFirst())
        StepVerifier.create(mono)
            .expectNext(MARIE)
            .verifyComplete()

        repository = ReactiveUserRepository(250, MARIE)
        repositoryWithDelay = ReactiveUserRepository(MIKE)
        mono = sut.useFastestMono(repository.findFirst(), repositoryWithDelay.findFirst())
        StepVerifier.create(mono)
            .expectNext(MIKE)
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun fastestFlux() {
        var repository: ReactiveRepository<User> = ReactiveUserRepository(MARIE, MIKE)
        var repositoryWithDelay: ReactiveRepository<User> = ReactiveUserRepository(250)
        var flux: Flux<User> = sut.useFastestFlux(repository.findAll(), repositoryWithDelay.findAll())
        StepVerifier.create(flux)
            .expectNext(MARIE, MIKE)
            .verifyComplete()

        repository = ReactiveUserRepository(250, MARIE, MIKE)
        repositoryWithDelay = ReactiveUserRepository()
        flux = sut.useFastestFlux(repository.findAll(), repositoryWithDelay.findAll())
        StepVerifier.create(flux)
            .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun complete() {
        val repository: ReactiveRepository<User> = ReactiveUserRepository()
        val probe: PublisherProbe<User> = PublisherProbe.of(repository.findAll())
        val completion: Mono<Void> = sut.fluxCompletion(probe.flux())
        StepVerifier.create(completion)
            .verifyComplete()
        probe.assertWasRequested()
    }

// ========================================================================================

    @Test
    fun nullHandling() {
        var mono: Mono<User> = sut.nullAwareUserToMono(User.SKYLER)
        StepVerifier.create(mono)
            .expectNext(User.SKYLER)
            .verifyComplete()
        mono = sut.nullAwareUserToMono(null)
        StepVerifier.create(mono)
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun emptyHandling() {
        var mono: Mono<User> = sut.emptyToSkyler(Mono.just(User.WALTER))
        StepVerifier.create(mono)
            .expectNext(User.WALTER)
            .verifyComplete()
        mono = sut.emptyToSkyler(Mono.empty())
        StepVerifier.create(mono)
            .expectNext(User.SKYLER)
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun collect() {
        val repository: ReactiveRepository<User> = ReactiveUserRepository()
        val collection: Mono<List<User>> = sut.fluxCollection(repository.findAll())
        StepVerifier.create(collection)
            .expectNext(listOf(User.SKYLER, User.JESSE, User.WALTER, User.SAUL))
            .verifyComplete()
    }
}
