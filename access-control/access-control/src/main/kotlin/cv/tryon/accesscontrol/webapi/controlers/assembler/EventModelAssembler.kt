package cv.tryon.accesscontrol.webapi.controlers.assembler

import cv.tryon.accesscontrol.domain.model.EventBO
import cv.tryon.accesscontrol.webapi.controlers.EventController
import cv.tryon.accesscontrol.webapi.model.output.EventOutputModel
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component

@Component
class EventModelAssembler : RepresentationModelAssemblerSupport<EventBO, EventOutputModel>(
    EventController::class.java, EventOutputModel::class.java,
) {

    override fun toModel(entity: EventBO): EventOutputModel {

        return EventOutputModel(
            sellerName = entity.sellerName,
            startDate = entity.startDate,
            endDate = entity.endDate,
        ).apply {
            this.add(
                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(EventController::class.java).find(id = entity.id)
                ).withSelfRel()
            )
            add(
                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(EventController::class.java).select()
                ).withRel("event: select")
            )
            add(
                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(EventController::class.java).delete(id = entity.id)
                ).withRel("event: delete")
            )
            add(
                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(EventController::class.java).update(id = entity.id)
                ).withRel("event: update")
            )
        }
    }
}

