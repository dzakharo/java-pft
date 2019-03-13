package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() {
    app.getContactHelper().contactCreation(new ContactData("Test Name7", "Test Name", "Test", "+79999999", "test@test.ru","test1"));
  }
}
