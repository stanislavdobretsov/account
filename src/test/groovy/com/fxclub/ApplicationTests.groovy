package com.fxclub

import com.fxbclub.account.DataSourceStub
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles(profiles = "test")
@Import([DataSourceStub])
class ApplicationTests extends Specification {

    def "Context loads"() {
        expect:
        1 == 1
    }

}
