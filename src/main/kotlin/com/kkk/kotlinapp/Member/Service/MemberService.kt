package com.kkk.kotlinapp.Member.Service

import com.kkk.kotlinapp.Member.Entity.Member
import com.kkk.kotlinapp.Member.Repository.MemberRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
class MemberService (private val memberRepository: MemberRepository){
    fun createMember(member: Member): Member = memberRepository.save(member)

    fun findById(id: Long): Member? = memberRepository.findById(id).orElse(null)

    fun findAll(): List<Member> = memberRepository.findAll()
}