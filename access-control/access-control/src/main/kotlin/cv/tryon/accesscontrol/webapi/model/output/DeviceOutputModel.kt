package cv.tryon.accesscontrol.webapi.model.output

import org.springframework.hateoas.RepresentationModel

data class DeviceOutputModel(
    val macAddress: String,
    val name: String,
    val model: String,
) : RepresentationModel<DeviceOutputModel>()
