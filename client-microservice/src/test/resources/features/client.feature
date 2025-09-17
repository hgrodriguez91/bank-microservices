Feature: Pruebas de API de Cliente

  Background: 'Set base url'
    Given url 'http://localhost:8080/clientes'

  Scenario: Crear cliente exitosamente
    Given request {  "name": "Jhon Doe", "gender": "M", "age": 32, "identification": "91111841927", "address": "Calle 51 #20F", "phone": "51052697", "password": "123456", "status": true  }
    When method POST
    Then status 201
    And match response.name == "Jhon Doe"
    And def clientId = response.id

    #Get client by id
    Given path clientId
    When method GET
    Then status 200
    And match response.id == clientId

    #Get all clients
    When method GET
    Then status 200
    And assert  response.length > 0

    #Update cliente
    Given request { "name": "Jhon Doe Watson",  "gender": "M",  "age": 33,  "identification": "91111841927",  "address": "Calle 51 #20F",  "phone": "51052697",  "password": "123456",  "status": true  }
    Given path clientId
    When method PUT
    Then status 200
    And match response.name == "Jhon Doe Watson"

    #Delete client
    Given path clientId
    Given method DELETE
    Then status 200
