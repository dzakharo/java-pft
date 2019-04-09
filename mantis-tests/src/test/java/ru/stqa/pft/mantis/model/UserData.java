package ru.stqa.pft.mantis.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "mantis_user_table")

public class UserData {


  @Id
  @Column(name = "id")
  private int id;
  @Expose
  @Column (name = "username")
  private  String username;

  public String getEmail() {
    return email;
  }

  @Expose
  @Column (name = "email")
  private  String email;


  public String getUsername(){
    return username;
  }


  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
