package cv.tryon.accesscontrol.webapi.controlers.constant

object ConstantUrl {
    const val START_EVENT: String = "event"
    const val START_DEVICE: String = "device"
    const val START_ENTRY: String = "entry"
    const val START_USER: String = "user"

    const val CREATE: String = "create"
    const val FIND: String = "/{id}"
    const val SELECT: String = "/select"
    const val UPDATE: String = "/update/{id}"
    const val DELETE: String = "/delete/{id}"

    const val VALID_TAG: String = "/valid/{eventId}/{tag}"
}