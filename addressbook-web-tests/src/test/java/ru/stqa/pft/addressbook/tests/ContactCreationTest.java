package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo =new File("src/test/resources/img.jpg");
    ContactData contact = new ContactData()
            .withFirstName("Name78").withLastName("Test5").withEmail("tet@tete.rr").withMobile("+5555").withPhoto(photo);
    app.contact().create(contact);
    assertThat(app.contact().count(),equalTo(before.size()+1));
    Contacts after=app.contact().all();
    assertThat(after,equalTo(before.withAdded(contact.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
  }

}
