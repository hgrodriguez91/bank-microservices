package com.devsu.client;


import com.intuit.karate.junit5.Karate;

class ClientTest {

    @Karate.Test
    Karate testClientFeature() {
        return Karate.run("classpath:features/client.feature");
    }
}
