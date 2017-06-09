Feature: BDD. Test Counterstring

  Scenario: A counterString using the default separator and terminator
    Given I have a CounterString
    When I generate a 20 character string
    Then I get "2^4^6^8^11^14^17^20*"


  Scenario: A counterString with explicit separator and terminator
    Given I have a CounterString with separator '-' and terminator '@'
    When I generate a 20 character string
    Then I get "2-4-6-8-11-14-17-20@"
