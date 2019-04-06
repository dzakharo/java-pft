package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("mobile"),contactData.getMobile());
    type(By.name("email"),contactData.getEmail());
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void returnToContacts() {
    click(By.id("content"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void confirmDeletion() {
    wd.switchTo().alert().accept();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void submitContectMofification() {
    click(By.xpath("//input[@name='update']"));
  }

  public void create(ContactData contact){
    initContactCreation();
    fillContactForm(contact);
    submitContactCreation();
    contactCache=null;
    returnToContacts();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactForm(contact);
    contactCache=null;
    submitContectMofification();
  }

  public void delete(ContactData contact) {
   selectContactById(contact.getId());
   deleteSelectedContact();
   confirmDeletion();
   contactCache=null;
   returnToContacts();
  }


  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache!=null) {
      return new Contacts(contactCache);
    }
    contactCache=new Contacts();
    List <WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element:elements){
      List<WebElement> line = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname=line.get(2).getText();
      String lastname=line.get(1).getText();
      String  allPhones = line.get(5).getText();
      String  allEmails = line.get(4).getText();
      String  address = line.get(3).getText();

      contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname).withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return  new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname=wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname=wd.findElement(By.name("lastname")).getAttribute("value");
    String home=wd.findElement(By.name("home")).getAttribute("value");
    String mobile=wd.findElement(By.name("mobile")).getAttribute("value");
    String work=wd.findElement(By.name("work")).getAttribute("value");
    String email=wd.findElement(By.name("email")).getAttribute("value");
    String email2=wd.findElement(By.name("email2")).getAttribute("value");
    String email3=wd.findElement(By.name("email3")).getAttribute("value");
    String address=wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withHome(home).withMobile(mobile).withWork(work).withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
  }
}
