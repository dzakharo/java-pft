package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData("Test Name7", "Test Name", "Test", "+79999999", "test@test.ru","test1"));
    }
  }
  @Test
  public void testContactModification(){
    List<ContactData> before = app.contact().getContactList();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"dNamde78", "Te5st Name", "Test", "+", "test@test.ru",null);
    int index =before.size()+1;
    app.contact().modify(contact, index);
    app.goTo().homePage();
    List<ContactData> after = app.contact().getContactList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size()-1);
    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId()) ;
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }


}
