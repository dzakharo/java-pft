package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData("Test Name7", "Test Name", "Test", "+79999999", "test@test.ru","test1"));
    }
  }

  @Test
  public void testContactDeletion() {

    List<ContactData> before = app.contact().getContactList();
    app.contact().delete();
    List<ContactData> after = app.contact().getContactList();
    Assert.assertEquals(after.size(),before.size()-1);
    before.remove(0);
    Assert.assertEquals(before,after);
  }

}