package com.devsu.account;

import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AccountTest {

    static {
        System.setProperty("spring.profiles.active", "test");
    }
    @Karate.Test
    Karate testClientFeature() {
        return Karate.run("classpath:features/account.feature");
    }
}
