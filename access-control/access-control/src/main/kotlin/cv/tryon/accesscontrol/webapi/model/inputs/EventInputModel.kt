package cv.tryon.accesscontrol.webapi.model.inputs

data class EventInputModel(
    val id: String,
    val sellerName: String,
    val startDate: String,
    val endDate: String,
    val tag: String
) 