package cv.tryon.accesscontrol.webapi.controlers.constant

object ConstantAuthority {
    const val CREATE_EVENT: String = "hasAuthority('create:event')"
    const val FIND_EVENT: String = "hasAuthority('find:event')"
    const val SELECT_EVENT: String = "hasAuthority('select:event')"
    const val UPDATE_EVENT: String = "hasAuthority('update:event')"
    const val DELETE_EVENT: String = "hasAuthority('delete:event')"
    const val VALID_tag: String = "hasAuthority('valid_tag:event')"

    const val CREATE_DEVICE: String = "hasAuthority('create:device')"
    const val FIND_DEVICE: String = "hasAuthority('find:device')"
    const val SELECT_DEVICE: String = "hasAuthority('select:device')"
    const val UPDATE_DEVICE: String = "hasAuthority('update:device')"
    const val DELETE_DEVICE: String = "hasAuthority('delete:device')"

    const val CREATE_ENTRY: String = "hasAuthority('create:entry')"
    const val FIND_ENTRY: String = "hasAuthority('find:entry')"
    const val SELECT_ENTRY: String = "hasAuthority('select:entry')"
    const val UPDATE_ENTRY: String = "hasAuthority('update:entry')"
    const val DELETE_ENTRY: String = "hasAuthority('delete:entry')"

    const val CREATE_USER: String = "hasAuthority('create:user')"
    const val FIND_USER: String = "hasAuthority('find:user')"
    const val SELECT_USER: String = "hasAuthority('select:user')"
    const val UPDATE_USER: String = "hasAuthority('update:user')"
    const val DELETE_USER: String = "hasAuthority('delete:user')"
}