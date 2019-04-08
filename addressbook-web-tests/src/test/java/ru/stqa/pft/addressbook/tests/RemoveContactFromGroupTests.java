package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstName("tttt").withLastName("tttt").withHome("111").withMobile("222").withEmail("email@email.com").inGroup(groups.iterator().next()));
    }
    if (app.db().groups().iterator().next().getContacts().size() == 0) {
      Groups groupList = app.db().groups();
      GroupData toGroup = groupList.iterator().next();
      Contacts contacts = app.db().contacts();
      ContactData selectedContact = contacts.iterator().next();
      app.contact().addContactToGroup(selectedContact, toGroup);
    }
  }

  @Test
  public void testRemovingContactFromGroup() {
    app.goTo().homePage();
    Contacts contactsBefore = app.db().contacts();
    Groups groupList = app.db().groups();
    ContactData selectedContact = contactsBefore.iterator().next();
    GroupData fromGroup = groupList.iterator().next();
    app.contact().removeContactFromGroup(selectedContact, fromGroup);
    Contacts contactsAfter = app.db().contacts();
    assertThat(contactsAfter.iterator().next().getGroups(), equalTo(contactsBefore.iterator().next().getGroups().without(fromGroup)));
    app.contact().goBackToAll();
    verifyContactListInUi();
  }
}