//package com.kkk.kotlinapp.config.Security.Annotation
//
//import com.kkk.kotlinapp.config.Security.DTO.AuthInfoDTO
//import com.kkk.kotlinapp.config.Security.JWTUtil
//import org.springframework.core.MethodParameter
//import org.springframework.stereotype.Component
//import org.springframework.web.bind.support.WebDataBinderFactory
//import org.springframework.web.context.request.NativeWebRequest
//import org.springframework.web.method.support.HandlerMethodArgumentResolver
//import org.springframework.web.method.support.ModelAndViewContainer
//
//@Component
//class LoginCheckMethodArgumentResolver : HandlerMethodArgumentResolver {
//
//    override fun supportsParameter(parameter: MethodParameter): Boolean {
//        return parameter.hasParameterAnnotation(JwtAuth::class.java)
//    }
//
//    override fun resolveArgument(
//        parameter: MethodParameter,
//        mavContainer: ModelAndViewContainer?,
//        webRequest: NativeWebRequest,
//        binderFactory: WebDataBinderFactory?
//    ): AuthInfoDTO {
//        return JWTUtil.getMemberInfo()
//    }
//}