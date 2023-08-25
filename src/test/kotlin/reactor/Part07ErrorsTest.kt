package reactor

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.domain.User
import reactor.test.StepVerifier

class Part07ErrorsTest {
    private val sut = Part07Errors()

// ========================================================================================

    @Test
    fun monoWithValueInsteadOfError() {
        var mono: Mono<User> = sut.betterCallSaulForBogusMono(Mono.error(IllegalStateException()))
        StepVerifier.create(mono)
            .expectNext(User.SAUL)
            .verifyComplete()

        mono = sut.betterCallSaulForBogusMono(Mono.just(User.SKYLER))
        StepVerifier.create(mono)
            .expectNext(User.SKYLER)
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun fluxWithValueInsteadOfError() {
        var flux: Flux<User> = sut.betterCallSaulAndJesseForBogusFlux(Flux.error(IllegalStateException()))
        StepVerifier.create(flux)
            .expectNext(User.SAUL, User.JESSE)
            .verifyComplete()

        flux = sut.betterCallSaulAndJesseForBogusFlux(Flux.just(User.SKYLER, User.WALTER))
        StepVerifier.create(flux)
            .expectNext(User.SKYLER, User.WALTER)
            .verifyComplete()
    }

// ========================================================================================

    @Test
    fun handleCheckedExceptions() {
        val flux: Flux<User> = sut.capitalizeMany(Flux.just(User.SAUL, User.JESSE))

        StepVerifier.create(flux)
            .verifyError(Part07Errors.GetOutOfHereException::class.java)
    }
}
