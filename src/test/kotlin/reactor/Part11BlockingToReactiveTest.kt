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
import kotlin.test.assertEquals
import kotlin.test.assertFalse

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
        val blockingRepository = BlockingUserRepository(*arrayOf())
        val complete: Mono<Void> = sut.fluxToBlockingRepository(reactiveRepository.findAll(), blockingRepository)

        assertEquals(0, blockingRepository.callCount)

        StepVerifier.create(complete)
            .verifyComplete()

        val it: Iterator<User> = blockingRepository.findAll().iterator()
        assertEquals(it.next(), User.SKYLER)
        assertEquals(it.next(), User.JESSE)
        assertEquals(it.next(), User.WALTER)
        assertEquals(it.next(), User.SAUL)
        assertFalse(it.hasNext())
    }
}
