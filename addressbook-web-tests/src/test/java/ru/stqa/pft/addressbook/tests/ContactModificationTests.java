package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size()==0){
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstName("test").withLastName("new").withEmail("tetete@fff.rr").withMobile("123456789"));
    }
  }

  @Test
  public void testContactModification(){
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData modifiedContact =before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("test").withLastName("test").withMobile("4444").withEmail("tetet@fd").withHome("55");
    app.contact().modify(contact);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));  }

}

