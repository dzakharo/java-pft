package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().getContactList();
    ContactData contact = new ContactData("Name78", "Te5st Name", "Test", "+", "test@test.ru","test1");
    app.contact().create(contact);
    List<ContactData> after = app.contact().getContactList();
    Assert.assertEquals(after.size(),before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (с1, с2) -> Integer.compare(с1.getId(),с2.getId()) ;
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
