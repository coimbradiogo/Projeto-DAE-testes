@US04
Feature: Guest Limitations Notice
  As a guest user
  I want to be informed about practice mode limitations
  So that I understand I do not earn coins or history

  Scenario: Show guest limitations message
    Given I have the device ready
    When I start the application
    And I tap the "Jogar como Convidado" button
    Then I see the guest limitations message
    And I close the AUT
