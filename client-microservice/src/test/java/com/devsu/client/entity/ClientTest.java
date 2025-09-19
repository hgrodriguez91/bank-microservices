package com.devsu.client.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    @Test
    void testPrePersistGeneratesClientId() {
        Client client = new Client();

        assertNull(client.getClientId());

        client.generateClientId();

        assertNotNull(client.getClientId());
        assertFalse(client.getClientId().isEmpty());

        String oldId = client.getClientId();
        client.generateClientId();
        assertEquals(oldId, client.getClientId());
    }

    @Test
    void testSetAndGetPersonFields() {
        Client client = new Client();
        client.setName("Juan Pérez");
        client.setGender("M");
        client.setAge(30);
        client.setIdentification("123456789");
        client.setAddress("Calle 123");
        client.setPhone("5551234567");
        client.setPassword("secret");
        client.setStatus(true);

        assertEquals("Juan Pérez", client.getName());
        assertEquals("M", client.getGender());
        assertEquals(30, client.getAge());
        assertEquals("123456789", client.getIdentification());
        assertEquals("Calle 123", client.getAddress());
        assertEquals("5551234567", client.getPhone());
        assertEquals("secret", client.getPassword());
        assertTrue(client.getStatus());
    }
}
