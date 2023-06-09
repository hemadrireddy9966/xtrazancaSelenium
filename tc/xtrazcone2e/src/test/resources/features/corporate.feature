Feature:xtrazcone


  @na
Scenario Outline: login as corporate
Given User opens corporate "<loginType>" login page
When User fills emailid "<mailId>"
And User fills the password "<Password>"
Then User should be able to verify dashboard
Examples:
| mailId                       | Password  |loginType           |
| sumitkumar21281104@gmail.com | 123456789 |Corporate/Enterprise|

