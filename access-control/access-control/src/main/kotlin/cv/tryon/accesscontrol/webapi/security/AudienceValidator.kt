package cv.tryon.accesscontrol.webapi.security

import org.springframework.security.oauth2.core.OAuth2Error
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult
import org.springframework.security.oauth2.jwt.Jwt

class AudienceValidator (private val audience : String) : OAuth2TokenValidator<Jwt>{
    private val errorCode = "invalid_token"
    private val description = "The required audience is missing"
    private val url : String? = null

    override fun validate(token: Jwt?): OAuth2TokenValidatorResult {
        val error = OAuth2Error(errorCode, description, url)

        return if( token != null && token.audience.contains(audience))
            OAuth2TokenValidatorResult.success()
        else
            OAuth2TokenValidatorResult.failure(error)
    }
}