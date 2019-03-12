package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm( new ContactData("Test Name7", "Test Name", "Test", "+79999999", "test@test.ru","test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToContacts();
  }
}
