package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().contactCreation(new ContactData("Test Name7", "Test Name", "Test", "+79999999", "test@test.ru","test1"));
    }
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("Test Name2", "Test Name2", "Test2", "+79999999", "test@test.ru",null),false );
    app.getContactHelper().submitContectMofification();
    app.getNavigationHelper().gotoHomePage();
  }
}
