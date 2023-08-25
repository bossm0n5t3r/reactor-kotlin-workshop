package reactor

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User
import reactor.repository.ReactiveUserRepository
import reactor.test.StepVerifier

class Part05MergeTest {
    private val sut = Part05Merge()

    companion object {
        private val MARIE = User("mschrader", "Marie", "Schrader")
        private val MIKE = User("mehrmantraut", "Mike", "Ehrmantraut")
    }

    private val repositoryWithDelay = ReactiveUserRepository(500)
    private val repository = ReactiveUserRepository(MARIE, MIKE)

// ========================================================================================

    @Test
    fun mergeWithInterleave() {
        val flux: Flux<User> = sut.mergeFluxWithInterleave(repositoryWithDelay.findAll(), repository.findAll())
        StepVerifier.create(flux)
            .expectNext(MARIE, MIKE, User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun mergeWithNoInterleave() {
        val flux: Flux<User> = sut.mergeFluxWithNoInterleave(repositoryWithDelay.findAll(), repository.findAll())
        StepVerifier.create(flux)
            .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL, MARIE, MIKE)
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun multipleMonoToFlux() {
        val skylerMono: Mono<User> = repositoryWithDelay.findFirst()
        val marieMono: Mono<User> = repository.findFirst()
        val flux: Flux<User> = sut.createFluxFromMultipleMono(skylerMono, marieMono)
        StepVerifier.create(flux)
            .expectNext(User.SKYLER, MARIE)
            .verifyComplete()
    }
}
