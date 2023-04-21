package cv.tryon.accesscontrol.webapi.model.inputs

data class DeviceInputModel(
    val macAddress: String,
    val name: String,
    val model: String,
)