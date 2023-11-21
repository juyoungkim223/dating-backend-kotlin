package com.riti.backendforfrontend.util

import com.riti.data.util.Util
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.xmlunit.builder.Transform.source
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.util.function.Predicate


@SpringBootTest
class UtilTests {
    @Test
    fun randomAlphabetNumber(): Any {
        val res = Util.randomAlphabetNumber(6)
        val r = StepVerifier
            .create<Any>(Mono.just("JOHN"))
            .expectNext("JOHN")
            .expectComplete()
            .verify()
        return r
    }
}