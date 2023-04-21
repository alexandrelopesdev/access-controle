package cv.tryon.accesscontrol.webapi.model.output

import org.springframework.hateoas.RepresentationModel

data class UserOutputModel(
    val name: String,
    val role: String,
    val company: String,
) : RepresentationModel<UserOutputModel>()