package cv.tryon.accesscontrol.webapi.security.helpers

import org.springframework.security.core.context.SecurityContextHolder

class SecurityHelper {

    fun validatePrincipal( ): String {
        val principal = SecurityContextHolder.getContext().authentication

        print("details: " + principal.details)

        return principal.name?: ""

    }
}