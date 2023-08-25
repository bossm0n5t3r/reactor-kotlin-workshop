package reactor

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User
import reactor.repository.BlockingUserRepository
import reactor.repository.ReactiveRepository
import reactor.repository.ReactiveUserRepository
import reactor.test.StepVerifier

class Part11BlockingToReactiveTest {
    private val sut = Part11BlockingToReactive()

// ========================================================================================

    @Test
    fun slowPublisherFastSubscriber() {
        val repository = BlockingUserRepository()
        val flux: Flux<User> = sut.blockingRepositoryToFlux(repository)
        assertThat(repository.callCount)
            .withFailMessage("The call to findAll must be deferred until the flux is subscribed")
            .isEqualTo(0)
        StepVerifier.create(flux)
            .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun fastPublisherSlowSubscriber() {
        val reactiveRepository: ReactiveRepository<User> = ReactiveUserRepository()
        val blockingRepository = BlockingUserRepository()
        val complete: Mono<Void> = sut.fluxToBlockingRepository(reactiveRepository.findAll(), blockingRepository)
        assertThat(blockingRepository.callCount).isEqualTo(0)
        StepVerifier.create(complete)
            .verifyComplete()
        val it: Iterator<User> = blockingRepository.findAll().iterator()
        assertThat(it.next()).isEqualTo(User.SKYLER)
        assertThat(it.next()).isEqualTo(User.JESSE)
        assertThat(it.next()).isEqualTo(User.WALTER)
        assertThat(it.next()).isEqualTo(User.SAUL)
        assertThat(it.hasNext()).isFalse()
    }
}
