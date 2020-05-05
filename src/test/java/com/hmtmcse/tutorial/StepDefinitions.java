package com.hmtmcse.tutorial;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

    @Before
    public void setup() {
        System.out.println("Setup Somethings");
    }

    @Given("Open google.com")
    public void open_google_com() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Open Google");
    }

    @When("Entering number {int} and {int}")
    public void entering_number_and(Integer first, Integer second) {
        System.out.println("First " + first + " Second " + second);
    }

    @When("Press enter")
    public void press_enter() {
        System.out.println("Press enter");
    }

    @Then("Result should be {int}")
    public void result_should_be(Integer result) {
        System.out.println("Result " + result);
        System.out.println("============================================================");
    }

    @After
    public void end() {
        System.out.println("Process End");
    }

}
