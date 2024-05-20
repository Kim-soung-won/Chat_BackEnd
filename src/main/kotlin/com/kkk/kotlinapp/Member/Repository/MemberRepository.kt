package com.kkk.kotlinapp.Member.Repository

import com.kkk.kotlinapp.Member.Entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>{
}