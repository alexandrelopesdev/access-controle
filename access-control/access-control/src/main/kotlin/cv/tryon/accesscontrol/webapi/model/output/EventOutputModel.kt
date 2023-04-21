package cv.tryon.accesscontrol.webapi.model.output

import org.springframework.hateoas.RepresentationModel

data class EventOutputModel(
    val sellerName: String,
    val startDate: String,
    val endDate: String,
) : RepresentationModel<EventOutputModel>()


