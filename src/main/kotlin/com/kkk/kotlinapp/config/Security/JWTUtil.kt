package com.kkk.kotlinapp.config.Security

import com.kkk.kotlinapp.config.Exceptions.NotAuthenticatedException
import com.kkk.kotlinapp.config.Security.DTO.AuthInfoDTO
import com.kkk.kotlinapp.config.Security.DTO.CustomUserDetails
import com.kkk.kotlinapp.config.Security.DTO.Member
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Component
class JWTUtil(
    @Value("\${jwt.secret}") secret:String
) {
    private val secretKey: SecretKey = SecretKeySpec(
        secret.toByteArray(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().algorithm
    )

    companion object {
        fun getMemberInfo(): AuthInfoDTO {
            // principal 정보를 가져온다.
            val principal: Any? = SecurityContextHolder.getContext().authentication.principal

            if (principal == "anonymousUser") {
                throw NotAuthenticatedException("스프링 시큐리티 세션에서 가져올 정보가 존재하지 않습니다.")
            }

            val userDetails: CustomUserDetails =
                SecurityContextHolder.getContext().authentication.principal as CustomUserDetails
            val member: Member = userDetails.member

            return AuthInfoDTO().apply {
                memberId = member.memberId
                email = member.email
                role = member.role
                memberNickName = member.memberNickname
            }
        }
    }

    // token 유효기간 검증
    fun isExpired(token: String): Boolean{
        var isExpired: Boolean = false

        try{
            isExpired =Jwts.parser() // JWT 파싱하기 위한 객체 생성, 토큰을 읽는다.
                .verifyWith(secretKey) // 비밀키를 활용해 JWT를 검증한다.
                .build() // JWT 파서 생성
                .parseSignedClaims(token) // Claims 객체를 반환한다.
                .payload // 페이로드 부분을 추출한다.
                .expiration // 만료기간을 추출한다.
                .before(Date()) // 그것이 현재 날짜, 시간과 비교한다.
        }catch (e: ExpiredJwtException){
            isExpired = true
        }
        return isExpired
    }

    fun getClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload // 페이로드 부분을 추출한다.
    }

    fun getCategory(token: String): String {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload // 페이로드 부분을 추출한다.
            .get("category", String::class.java)
    }
}