package com.sktech.academicportal.entity;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "User_Profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "followers")
    private String followers;

    @Column(name = "following")
    private String following;

    @Column(name = "friends")
    private String friends;

    @Column(name = "skills")
    private String skills;

    @Column(name = "note")
    private String note;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;







}
