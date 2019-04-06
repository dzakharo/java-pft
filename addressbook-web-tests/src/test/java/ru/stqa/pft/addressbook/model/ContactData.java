package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id= Integer.MAX_VALUE;
  private  String firstname;
  private  String lastname;
  private  String mobile;
  private  String email;

  public ContactData withId(int id){
  this.id =id;
  return this;
  }

  public ContactData withFirstName(String firstname){
  this.firstname=firstname;
  return this;
  }

  public  ContactData withLastName(String lastname){
    this.lastname=lastname;
    return this;
  }

  public  ContactData withEmail(String email){
    this.email=email;
    return this;
  }

  public  ContactData withMobile(String mobile){
    this.mobile=mobile;
    return this;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }
}
