Feature: Test addition using Google Calculator


  Scenario Outline: Test 2 number addition
    Given Open google.com
    When Entering number <firstNumber> and <secondNumber>
    And Press enter
    Then Result should be <result>

    Examples:
      | firstNumber | secondNumber | result |
      | 1           | 1            | 2      |
      | 6           | 6            | 12     |
      | 8           | 5            | 13     |


