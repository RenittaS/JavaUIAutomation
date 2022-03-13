#Пишем на основании сценария: src/test/java/allure/pageobject/flor2u/PageObjectTest.java
Feature: I forgot my password and ask to change it

  Scenario Outline: Send a password change request
    Given I am on MainPage
    When I click Enter button
    And I click Forgot your password link
    And I send '<Email>'
    Then I see new page with success message
    Examples:
    | Email    |
    | krasnova.irina@gmail.com |
