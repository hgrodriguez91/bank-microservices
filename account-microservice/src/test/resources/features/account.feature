Feature: Pruebas del AccountController

  Background:
    # Base URL de tu microservicio
    * url 'http://localhost:8080'

  # 1. Obtener todas las cuentas
  Scenario: Obtener todas las cuentas
    Given path 'cuentas'
    When method get
    Then status 200
    And match response == '#[]'

  # 2. Obtener cuentas de un cliente
  Scenario: Obtener cuentas de un cliente
    Given path 'cuentas/cliente/1'
    When method get
    Then status 200
    And match response == '#[]'

  # 3. Obtener cuenta por ID
  Scenario: Obtener cuenta por ID
    Given path 'cuentas/1'
    When method get
    Then status 404

  # 4. Crear cuenta
  Scenario: Crear una cuenta
    Given path 'cuentas'
    And request { clientId: 1, accountType: "AHORRO", initialAmount: 1000, accountNumber: 123456 }
    When method post
    Then status 201
    And match response ==  {"id":1,"accountNumber":"123456","accountType":"AHORRO","initialAmount":1000,"status":true,"clientId":1}


  # 5. Consultar historial de transacciones
  Scenario: Obtener historial de transacciones
    Given path 'cuentas/reportes/1'
    And param fechaInicial = '2025-09-01'
    And param fechaFinal = '2025-09-18'
    When method get
    Then status 200
    And match response == '#[]'
