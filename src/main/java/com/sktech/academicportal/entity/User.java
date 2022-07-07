package com.sktech.academicportal.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
public class User {

    // User (Student, Teacher and all other role)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Column(name = "email", nullable = false, length = 40)
    private String email;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "photos", length = 64)
    private String photos;

    @Column(name = "enabled", length = 40)
    private boolean enabled;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


    // User (Student)
    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mother_name")
    private String motherName;


    @Column(name = "admission_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date admissionDate;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;


    @Column(name = "class_roll")
    private Integer classRoll;

    @Column(name = "current_class")
    private String currentClass;

    @Column(name = "class_section")
    private String classSection;

    @Column(name = "academic_ID")
    private String academicID;

    @Column(name = "contact_No")
    private String contactNo;

    @Column(name = "address")
    private String address;

    // User (Teacher and all other role)

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @Transient
    public String getPhotosImagePath() {
        if (id == null || photos == null) {
            return "/images/default-user.png";
        }
        return "/user-photos/" + id + "/" + this.photos;
    }

    @Transient
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Transient
    public Set<Role> getRoles() {
        return roles;
    }


}
