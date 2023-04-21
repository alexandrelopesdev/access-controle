package cv.tryon.accesscontrol.domain.adapters

import cv.tryon.accesscontrol.data.model.Event
import cv.tryon.accesscontrol.domain.model.EventBO

class EventAdapter {

    companion object {

        fun toModel(event: Event) = EventBO(
            id = event.id,
            sellerName = event.sellerName,
            startDate = event.startDate,
            endDate = event.endDate,
        )
        fun toModel(event: EventBO) = Event(
            id = event.id,
            sellerName = event.sellerName,
            startDate = event.startDate,
            endDate = event.endDate,
        )

    }
}