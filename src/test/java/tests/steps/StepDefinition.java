package tests.steps;

import TestClasses.Steps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import jdk.jfr.Name;

public class StepDefinition extends Steps {
    @Given("create {string} card with name {string}")
    public void createRubCard(String type, String name) {
        createCard(type, name);
    }

    @And("check parameters of created card: {string}, {string}, {string}")
    public void checkCreatedParams(String classType, String name, String balance) {
        checkCard(classType, name, balance);
    }

    @Given("create Currency card with {string} name with {string} currency type")
    public void createCurrCard(String name, String currencyType) {
        createCurrencyCard(name, currencyType);
    }

    @Given("create deposit with name {string} and currency type {string}")
    public void createDeposit(String name, String currencyType) {
        createDepositStep(name, currencyType);
    }

    @And("check params of created deposit: {string}, {string}, {string}, {string}")
    public void checkDeposit(String classType, String currencyType, String balance, String name) {
        checkDepositStep(classType, currencyType, balance, name);
    }

    @Then("add {string} to current balance card with name {string}")
    public void addToBalance(String balance, String name) {
        addToCurrentBalanceStep(balance, name);
    }

    @And("check current card with name {string} balance = {string}")
    public void checkBalance(String name, String balance){
        checkBalanceStep(balance, name);
    }
}
