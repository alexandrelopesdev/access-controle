package cv.tryon.accesscontrol.webapi.security.helpers;

import org.springframework.security.core.GrantedAuthority
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*
import java.util.stream.Collectors

class KeycloakRealmRoleConverter : Converter<Jwt?, Collection<GrantedAuthority?>?> {
        private var resourceName: String? = null
    private var roleForRealm = true

    constructor() {}
    constructor(roleForRealm: Boolean, resourceName: String?) {
        this.roleForRealm = roleForRealm
        this.resourceName = resourceName
    }


    companion object {
        const val REALM_ACCESS = "realm_access"
        const val RESOURCE_ACCESS = "resource_access"
        const val ROLES = "roles"
        const val ROLE_PREFIX = ""
    }

    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
        var access: Map<String?, Any?>?
        if (!roleForRealm && resourceName != null) {
            access = jwt.claims[RESOURCE_ACCESS] as Map<String?, Any?>?
            if (access!!.containsKey(resourceName)) {
                access = access[resourceName] as Map<String?, Any?>?
            }
        } else {
            access = jwt.claims[REALM_ACCESS] as Map<String?, Any?>?
        }
        return (access!![ROLES] as List<String>?)!!.stream()
            .map { roleName: String ->
                ROLE_PREFIX + roleName
            }
            .map { role: String? ->
                SimpleGrantedAuthority(
                    role
                )
            }
            .collect(Collectors.toList())
    }

}