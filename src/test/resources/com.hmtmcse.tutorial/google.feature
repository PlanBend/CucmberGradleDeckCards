Feature: Test addition using Google Calculator


  Scenario Outline: Test 2 number addition
    Given Open google.com
    When Entering number <firstNumber> and <secondNumber>
    And Press enter
    Then Result should be <result>

    Examples:
      | firstNumber | secondNumber | result |
      | 6           | 6            | 12     |
      | 6           | 2            | 9      |
      | 8           | 5            | 13     |


