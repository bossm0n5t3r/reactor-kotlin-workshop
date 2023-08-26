package reactor

import reactor.core.publisher.Flux
import reactor.domain.User
import java.util.function.Supplier

/**
 * Learn how to use StepVerifier to test Mono, Flux or any other kind of Reactive Streams Publisher.
 *
 * @see <a href="https://projectreactor.io/docs/test/release/api/reactor/test/StepVerifier.html">
 *     StepVerifier Javadoc
 *     </a>
 */
class Part03StepVerifier {

// ========================================================================================

    fun expectFooBarComplete(flux: Flux<String>) {
        TODO(
            "Use StepVerifier to check that " +
                "the flux parameter emits \"foo\" and \"bar\" elements then completes successfully.",
        )
    }

// ========================================================================================

    fun expectFooBarError(flux: Flux<String>) {
        TODO(
            "Use StepVerifier to check that " +
                "the flux parameter emits \"foo\" and \"bar\" elements then a RuntimeException error.",
        )
    }

// ========================================================================================

    fun expectSkylerJesseComplete(flux: Flux<User>) {
        TODO(
            "Use StepVerifier to check that " +
                "the flux parameter emits a User with \"swhite\" username and another one with \"jpinkman\" " +
                "then completes successfully.",
        )
    }

// ========================================================================================

    fun expect10Elements(flux: Flux<Long>) {
        TODO("Expect 10 elements then complete and notice how long the test takes.")
    }

// ========================================================================================

    fun expect3600Elements(supplier: Supplier<Flux<Long>>) {
        TODO(
            "Expect 3600 elements at intervals of 1 second, and verify quicker than " +
                "3600s by manipulating virtual time thanks to StepVerifier#withVirtualTime, " +
                "notice how long the test takes",
        )
    }
}
