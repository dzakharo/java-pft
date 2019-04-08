package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstName("Test").withLastName("Teeest").withAddress("Saint-Petersburg").withHome("111").withMobile("222").withWork("333").withEmail("email@email.com"));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
    if (app.db().contacts().iterator().next().getGroups().size() == app.db().groups().size()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }

  @Test
  public void testAddContactToGroup() {
    app.goTo().homePage();
    Contacts contactsBefore = app.db().contacts();
    Groups groupList = app.db().groups();
    ContactData selectedContact = contactsBefore.iterator().next();
    GroupData toGroup = groupList.iterator().next();
    app.contact().addContactToGroup(selectedContact, toGroup);
    Contacts contactsAfter = app.db().contacts();
    assertThat(contactsAfter.iterator().next().getGroups(), equalTo(contactsBefore.iterator().next().getGroups().withAdded(toGroup)));
    verifyContactListInUi();
  }

}