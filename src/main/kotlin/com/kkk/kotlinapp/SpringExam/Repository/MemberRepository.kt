package com.kkk.kotlinapp.SpringExam.Repository

import com.kkk.kotlinapp.SpringExam.Entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>{
}