package reactor.repository

import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.time.Duration

class ReactiveUserRepository : ReactiveRepository<User> {

    companion object {
        private const val DEFAULT_DELAY_IN_MS = 100L
    }

    private val delayInMs: Long = DEFAULT_DELAY_IN_MS
    private val users = mutableListOf(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)

    override fun save(publisher: Publisher<User>): Mono<Void> {
        return withDelay(Flux.from(publisher)).doOnNext { u -> this.users.add(u) }.then()
    }

    override fun findFirst(): Mono<User> {
        return withDelay(users[0].toMono())
    }

    override fun findAll(): Flux<User> {
        return withDelay(users.toFlux())
    }

    override fun findById(id: String): Mono<User> {
        val user = users.stream()
            .filter { p -> p.username == id }
            .findFirst()
            .orElseThrow { IllegalArgumentException("No user with username $id found!") }
        return withDelay(user.toMono())
    }

    private fun withDelay(userMono: Mono<User>): Mono<User> {
        return Mono.delay(Duration.ofMillis(delayInMs)).flatMap { userMono }
    }

    private fun withDelay(userFlux: Flux<User>): Flux<User> {
        return Flux.interval(Duration.ofMillis(delayInMs))
            .zipWith(userFlux) { _: Long, user -> user }
    }
}
