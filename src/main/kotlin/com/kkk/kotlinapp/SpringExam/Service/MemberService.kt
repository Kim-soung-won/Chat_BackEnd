package com.kkk.kotlinapp.SpringExam.Service

import com.kkk.kotlinapp.SpringExam.Entity.Member
import com.kkk.kotlinapp.SpringExam.Repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService (private val memberRepository: MemberRepository){
    fun createMember(member: Member): Member = memberRepository.save(member)

    fun findById(id: Long): Member? = memberRepository.findById(id).orElse(null)

    fun findAll(): List<Member> = memberRepository.findAll()
}