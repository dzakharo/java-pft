package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("Contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id= Integer.MAX_VALUE;

  @Column(name = "firstname")
  private  String firstname;

  @Column(name = "lastname")
  private  String lastname;

  @Column(name = "mobile")
  @Type(type = "text")
  private  String mobile;

  @Column(name = "email")
  @Type(type = "text")
  private  String email;

  @Column(name = "email2")
  @Type(type = "text")
  private  String email2;

  @Column(name = "email3")
  @Type(type = "text")
  private  String email3;

  @Column(name = "address")
  @Type(type = "text")
  private  String address;

  @Transient
  private  String allEmails;

  @Column(name = "home")
  @Type(type = "text")
  private  String home;

  @Column(name = "work")
  @Type(type = "text")
  private  String work;

  @Transient
  private  String allPhones;

  @Transient
  private String photo;

  @ManyToMany (fetch=FetchType.EAGER)
  @JoinTable(name="address_in_groups",
          joinColumns = @JoinColumn(name="id"),inverseJoinColumns = @JoinColumn(name="group_id"))
    private Set<GroupData> groups=new HashSet<GroupData>();

  public Groups getGroups() {
    return new Groups(groups);
  }

  public File getPhoto() {
    if (photo == null) {
      return null;
    } else {
      return new File(photo);
    }
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

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
  public  ContactData withHome(String home){
    this.home=home;
    return this;
  }
  public  ContactData withWork(String work){
    this.work=work;
    return this;
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
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

  public String getHome() {
    return home;
  }

  public String getWork() {
    return work;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
    if (home != null ? !home.equals(that.home) : that.home != null) return false;
    return email != null ? email.equals(that.email) : that.email == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (home != null ? home.hashCode() : 0);
    return result;
  }

}
