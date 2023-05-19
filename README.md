# APIAutomationFrameworkExample
This is an example end to end test of a Maven project with Cucumber and Rest Assured dependencies, reusable POJO classes for serializing and deserializing JSON payload for request and response specifications, logging framework to capture request and response details, validations and assertions, driving global variables from properties file, Enum class with API resource methods, data driven mechanism to drive data dynamically from Feature files, parameterization to run tests with multiple data sets using Cucumber Example keyword, Tagging mechanism to run selected tests through TestRunner class, Pre and Post conditions for select tests with Cucumber Hooks, HTML reports for test execution results and so on.  
Two test scenarios can be found in /src/test/java/features/placeValidations.feature file in plain English language. 
1. First scenario @AddPlace calls "AddPlace" API which adds a new place to maps, verifies response status code and response body with assertions and then we call "GetPlace" API which retrieves the newly created existing place by it's ID and verifies that it matches the ID from "AddPlace" API response. 
2. Second scenario @DeletePlace calls "DeletePlace" API to delete existing place. We pass the same place ID which we got from first scenario (This will only work if your run @AddPlace scenario prior to running @DeletePlace scenario).
If only @DeletePlace scenario needs to be tested, then there is a Hooks class with Pre-condition for @DeletePlace scenario which will check if place ID is null or not. If ID is null then it will send an AddPlace API to get a new place with new ID and only then the test will proceed with @DeletePlace scenario. It will send the request and verify the response body is "OK". 

To run tests in terminal using Maven commands: 
1. mvn test verify - to get HTML report of completed test run in projectDirectory/target/cucumber-html-reports/overview-features.html
2. mvn test -Dcucumber.options="--tags @AddPlace" - to run @AddPlace test scenario or @DeletePlace for delete test scenario

To see response and request details in txt file of a completed test run: projectDirectory/logging.txt 

GO TO -> Master branch 
