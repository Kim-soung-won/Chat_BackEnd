package com.kkk.kotlinapp.Member.Entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import lombok.*

@Entity
@Getter
@Table(name = "member")
class Member (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name="name")
    @NotEmpty(message = "Member name field can't be empty")
    var name: String,

    @Column(name="email")
    @NotEmpty(message = "Member email field can't be empty")
    var email: String,

    @Column(name="password")
    @NotEmpty(message = "Member password field can't be empty")
    var password: String,
)