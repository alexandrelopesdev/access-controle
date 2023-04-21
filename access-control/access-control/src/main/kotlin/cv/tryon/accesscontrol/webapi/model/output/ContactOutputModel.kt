package cv.tryon.accesscontrol.webapi.model.output

import org.springframework.hateoas.RepresentationModel


data class ContactOutputModel(
    val phoneNumber: String,
    val adrres: String,
    val email: String,
) : RepresentationModel<ContactOutputModel>()

