Feature:xtrazcone


  @le
  Scenario Outline: login as learner
    Given User opens learner "<loginType>" login page
    When User fills emailid "<mailId>" as learner
    And User fills the password "<Password>" as learner
    Then User should be able to verify learner dashboard
    Examples:
      | mailId                   | Password  |loginType         |
      | bainadepriti4s@gmail.com | 123456789 |Individual learner|
