package ru.stqa.pft.addressbook;

import org.testng.annotations.*;


public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() {
    initContactCreation();
    fillContactForm( new ContactData("Test Name", "Test Name", "Test", "+79999999", "test@test.ru"));
    submitContactCreation();
    returnToContacts();

  }

}
