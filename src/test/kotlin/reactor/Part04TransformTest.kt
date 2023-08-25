package reactor

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User
import reactor.repository.ReactiveUserRepository
import reactor.test.StepVerifier

class Part04TransformTest {
    private val sut = Part04Transform()
    private val repository = ReactiveUserRepository()

// ========================================================================================

    @Test
    fun transformMono() {
        val mono: Mono<User> = repository.findFirst()
        StepVerifier.create(sut.capitalizeOne(mono))
            .expectNext(User("SWHITE", "SKYLER", "WHITE"))
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun transformFlux() {
        val flux: Flux<User> = repository.findAll()
        StepVerifier.create(sut.capitalizeMany(flux))
            .expectNext(
                User("SWHITE", "SKYLER", "WHITE"),
                User("JPINKMAN", "JESSE", "PINKMAN"),
                User("WWHITE", "WALTER", "WHITE"),
                User("SGOODMAN", "SAUL", "GOODMAN"),
            )
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun asyncTransformFlux() {
        val flux: Flux<User> = repository.findAll()
        StepVerifier.create(sut.asyncCapitalizeMany(flux))
            .expectNext(
                User("SWHITE", "SKYLER", "WHITE"),
                User("JPINKMAN", "JESSE", "PINKMAN"),
                User("WWHITE", "WALTER", "WHITE"),
                User("SGOODMAN", "SAUL", "GOODMAN"),
            )
            .verifyComplete()
    }
}
