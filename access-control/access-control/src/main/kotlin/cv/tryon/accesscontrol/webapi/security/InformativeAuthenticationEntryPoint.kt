package cv.tryon.accesscontrol.webapi.security

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.core.OAuth2Error
import org.springframework.security.oauth2.jwt.JwtValidationException
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint
import org.springframework.security.web.AuthenticationEntryPoint


class InformativeAuthenticationEntryPoint : AuthenticationEntryPoint{
    private val delegate : BearerTokenAuthenticationEntryPoint =
        BearerTokenAuthenticationEntryPoint()
    private val mapper : ObjectMapper = ObjectMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)

    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?,
                          authException: AuthenticationException?) {

        delegate.commence(request,response, authException)

        if(authException?.cause is  JwtValidationException){
            val exception = authException.cause as JwtValidationException
            val errors : Collection<OAuth2Error> = exception.errors
            mapper.writeValue(response?.writer, errors)
        }

    }
}