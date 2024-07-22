package com.kkk.kotlinapp.config.Security.DTO

import jakarta.persistence.*
import lombok.*

@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    // 필드 값을 동적으로 변경할 수 있어야 하기 때문에 val이 아닌 var를 사용한다.
    var memberId: Long? = null

    @Column(name = "email", unique = true)
    lateinit var email: String

    @Column(name = "password")
    var password: String? = null

    @Column(name = "member_name")
    var memberName: String? = null

    @Column(name = "member_nickname", unique = true)
    lateinit var memberNickname: String

    @Column(name = "member_address")
    var memberAddress: String? = null

    @Column(name = "member_birth")
    var memberBirth: String? = null

    @Column(name = "member_gender")
    var memberGender: String? = null

    @Column(name = "member_phone")
    var memberPhone: String? = null

    @Enumerated(EnumType.STRING)
    var role: Role? = null

    @Column(name = "member_image")
    var memberImage: String? = null

    @Column(name = "login_type")
    var loginType: String? = null

    @Column(name = "is_deleted")
    var isDeleted = false

    @Builder
    fun Member(
        email: String?, password: String?, memberName: String?, memberNickname: String?, memberAddress: String?,
        memberGender: String?, memberPhone: String?,
        memberImage: String?,
        role: Role?
    ) {
        this.email = email!!
        this.password = password
        this.memberName = memberName
        this.memberNickname = memberNickname!!
        this.memberAddress = memberAddress
        this.memberGender = memberGender
        this.memberPhone = memberPhone
        this.memberImage = memberImage
        this.role = role
    }

    // 인증용 토큰에 넣을 회원 생성자
    fun Member(memberId: Long?, email: String?, role: Role?, memberNickname: String?) {
        this.memberId = memberId
        this.email = email!!
        this.role = role
        this.memberNickname = memberNickname!!
    }
}