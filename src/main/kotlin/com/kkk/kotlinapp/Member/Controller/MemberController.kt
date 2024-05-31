package com.kkk.kotlinapp.Member.Controller

import com.kkk.kotlinapp.Member.DTO.MemberFindDTO
import com.kkk.kotlinapp.Member.DTO.MemberInsertDTO
import com.kkk.kotlinapp.Member.Entity.Member
import com.kkk.kotlinapp.Member.Service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(private val memberService: MemberService) {

    @PostMapping("/api/POST/member")
    fun insertMember(@RequestBody request: MemberInsertDTO): ResponseEntity<Any> {
        return try {
            val member = Member(
                name = request.name,
                email = request.email,
                password = request.password
            )
            memberService.createMember(member)
            ResponseEntity.status(201).body("Success")
        } catch (e:Exception){
            ResponseEntity.status(400).body("Error creating member : ${e.message}")
        }
    }

    @GetMapping("/api/GET/member")
    fun getMemberById(@RequestParam(name="id")id:Long): ResponseEntity<Any>{
        println(id)
        val member: Member = memberService.findById(id) ?: return ResponseEntity.status(404).body("Member not found")
        println(member.name)
        return ResponseEntity.status(201).body(member)
    }
    @GetMapping("/api/GET/memberList")
    fun getMemberById(): ResponseEntity<Any>{
        val members: List<Member> = memberService.findAll()
        println(members.get(0).name)
        println(members.size)
        return ResponseEntity.status(201).body(members)
    }

    @GetMapping("/api/hello")
    fun sayHello(): String{
        return "Hello, World!"
    }
}