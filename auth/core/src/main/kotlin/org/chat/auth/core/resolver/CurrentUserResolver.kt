package org.chat.auth.core.resolver

import org.chat.auth.core.vo.AuthUser
import org.chat.auth.core.vo.CurrentUser
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class CurrentUserResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val hasAnnotation = parameter.hasParameterAnnotation(CurrentUser::class.java)
        val isStringType = parameter.parameterType == Long::class.java
        return hasAnnotation && isStringType
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any {
        val authentication = SecurityContextHolder.getContext().authentication
        val authUser = (authentication.principal as? AuthUser)
            ?: throw IllegalStateException("Authentication is not AuthUser")
        return authUser.id
    }
}