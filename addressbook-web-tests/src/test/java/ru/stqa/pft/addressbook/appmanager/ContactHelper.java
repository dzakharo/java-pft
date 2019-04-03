package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("middlename"),contactData.getMiddlename());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("mobile"),contactData.getMobile());
    type(By.name("email"),contactData.getEmail());

    if(creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isAlertPresent(By.name("new group")));
    }
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

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void confirmDeletion() {
    wd.switchTo().alert().accept();
  }

  public void editContact(int count) {
    click(By.xpath("//table[@id='maintable']/tbody/tr["+count+"]/td[8]/a/img"));
  }

  public void submitContectMofification() {
    click(By.xpath("//input[@name='update']"));
  }

  public void create(ContactData contact){
    initContactCreation();
    fillContactForm(contact,true);
    submitContactCreation();
    returnToContacts();
  }

  public void modify(ContactData contact, int index) {
    editContact(index);
    fillContactForm(contact,false);
    submitContectMofification();
  }

  public void delete() {
   selectContact();
   deleteSelectedContact();
   confirmDeletion();
   returnToContacts();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>() ;
    List <WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element:elements){
      List<WebElement> line = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname=line.get(2).getText();
      String lastname=line.get(1).getText();
      ContactData contact=new ContactData(id, firstname, lastname);
      contacts.add(contact);
    }
    return contacts;
  }
}
