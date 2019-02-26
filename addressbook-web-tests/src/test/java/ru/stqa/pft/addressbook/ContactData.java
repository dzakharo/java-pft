package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String mobile;
  private final String email;

  public ContactData(String firstname, String middlename, String lastname, String mobile, String email) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.mobile = mobile;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
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
}
