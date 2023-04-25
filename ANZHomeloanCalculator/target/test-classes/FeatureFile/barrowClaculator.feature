
Feature: Verify the Calculator working
  I want to use this template for my feature file

  
  Scenario: verification of working of Barrow Calculator
    Given I want use this URL and open the application
    When  I want to click the details of applicationtype
    And   enter the number of depenedents
    And select the type of Home BarrowType
    And enter the earnings of annaulIncome and OtherIncome
    And enter the expenses of LivingExpenses and HomeLoanRepayments and OtherLoanRepayments
    And enter the expenses of MonthlyCommitments and CreditCard
    Then I click the barrow button
  
   Scenario: checks the StartOver button
    Then I clicks on the startover button 
    
    
   Scenario:
   When I enter the expenses of LivingExpenses
   Then I should see the error mesaage

  