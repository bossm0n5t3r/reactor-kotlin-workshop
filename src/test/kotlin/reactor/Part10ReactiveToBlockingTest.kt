package reactor

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User
import reactor.repository.ReactiveRepository
import reactor.repository.ReactiveUserRepository

class Part10ReactiveToBlockingTest {
    private val sut = Part10ReactiveToBlocking()
    private val repository: ReactiveRepository<User> = ReactiveUserRepository()

// ========================================================================================

    @Test
    fun mono() {
        val mono: Mono<User> = repository.findFirst()
        val user: User = sut.monoToValue(mono)
        assertThat(user).isEqualTo(User.SKYLER)
    }

// ========================================================================================

    @Test
    fun flux() {
        val flux: Flux<User> = repository.findAll()
        val users: Iterable<User> = sut.fluxToValues(flux)
        val it: Iterator<User> = users.iterator()
        assertThat(it.next()).isEqualTo(User.SKYLER)
        assertThat(it.next()).isEqualTo(User.JESSE)
        assertThat(it.next()).isEqualTo(User.WALTER)
        assertThat(it.next()).isEqualTo(User.SAUL)
        assertThat(it.hasNext()).isFalse()
    }
}
