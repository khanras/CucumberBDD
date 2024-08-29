Feature: TestCase1

  @tc01
  Scenario: Test case 01
    Given User check if any broken link available in the page
  #Given User on the home page
  #When Login using valid credentials
  #Then User can login successfully
  @tc02
  Scenario: Test case 02
    Given User on the home page
    When Login using valid credentials
    Then User can login successfully

  @testCase03
  Scenario Outline: Handle multiple windows in selenium
    Given User on the home page of <URL>
    When User click on links to open multiple window
    Then User validate the windows

    Examples:
      | URL                                  |
      | 'https://demoqa.com/browser-windows' |
      #| 'https://demoqa.com/browser-windows' |
      #| 'https://demoqa.com/browser-windows' |
