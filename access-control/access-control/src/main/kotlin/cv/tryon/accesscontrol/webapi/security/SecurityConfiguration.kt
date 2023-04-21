package cv.tryon.accesscontrol.webapi.security


import cv.tryon.accesscontrol.webapi.security.helpers.KeycloakRealmRoleConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.jwt.*
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfiguration  {

    @Value("\${auth0.audience}")
    private val audience: String = ""

    @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private val issuer: String = ""

    @Value("\${auth.resource-name}")
    protected var resourceName: String = ""

    @Value("\${auth.for-realm}")
    protected var roleForRealm = true


    fun configure(http: HttpSecurity?) {
        http?.authorizeHttpRequests()
            ?.requestMatchers ("")?.permitAll()
                ?.and()?.cors()?.configurationSource(corsConfigurationSource())
                ?.and()?.oauth2ResourceServer()
                ?.authenticationEntryPoint(InformativeAuthenticationEntryPoint())
                ?.jwt()?.decoder(jwtDecoder())
                ?.jwtAuthenticationConverter(jwtAuthenticationConverter())

        http?.csrf()?.disable();
        http?.headers()?.frameOptions()?.disable()
    }

    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedMethods = listOf(
                HttpMethod.GET.name(),
                HttpMethod.PUT.name(),
                HttpMethod.POST.name(),
                HttpMethod.DELETE.name()
        )
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues())
        return source
    }

    fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
        val converter = JwtGrantedAuthoritiesConverter()

        /*converter.setAuthoritiesClaimName("permissions")
        converter.setAuthorityPrefix("")*/

        val jwtConverter = JwtAuthenticationConverter()
        jwtConverter.setJwtGrantedAuthoritiesConverter( KeycloakRealmRoleConverter(roleForRealm, resourceName))

        return jwtConverter
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        val jwtDec: NimbusJwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer)

        val audienceValidator: OAuth2TokenValidator<Jwt> = AudienceValidator(audience)
        val withIssuer: OAuth2TokenValidator<Jwt> = JwtValidators.createDefaultWithIssuer(issuer)
        val withAudience: OAuth2TokenValidator<Jwt> = DelegatingOAuth2TokenValidator(withIssuer, audienceValidator)

        jwtDec.setJwtValidator(withAudience)

        return jwtDec
    }

}