package cv.tryon.accesscontrol.webapi.model.output

import org.springframework.hateoas.RepresentationModel

data class EntryOutputModel(
    val userTag: String,
    val createdAt: String,
    val updatedAt: String,
) : RepresentationModel<EntryOutputModel>()