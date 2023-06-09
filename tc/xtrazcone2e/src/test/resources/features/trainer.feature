Feature:xtrazcone


  @tr
  Scenario Outline: login as trainer
    Given User opens trainer "<loginType>" login page
    When User fills emailid "<mailId>" as trainer
    And User fills the password "<Password>" as trainer
    Then User should be able to verify trainer dashboard
    Examples:
      | mailId               | Password  |loginType       |
      | rinkalpatil@yahoo.in | 123456789 |Author / Trainer|
