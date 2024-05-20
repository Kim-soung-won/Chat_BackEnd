package com.kkk.kotlinapp.Member.Controller

import com.kkk.kotlinapp.Member.DTO.MemberFindDTO
import com.kkk.kotlinapp.Member.DTO.MemberInsertDTO
import com.kkk.kotlinapp.Member.Entity.Member
import com.kkk.kotlinapp.Member.Service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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
    fun getMemberById(@RequestBody request: MemberFindDTO): ResponseEntity<Any>{
        println(request.id)
        val member: Member = memberService.findById(request.id) ?: return ResponseEntity.status(404).body("Member not found")
        return ResponseEntity.status(201).body(member)
    }
}