package cv.tryon.accesscontrol.webapi.controlers.assembler

import cv.tryon.accesscontrol.domain.model.EntryBO
import cv.tryon.accesscontrol.webapi.controlers.EntryController
import cv.tryon.accesscontrol.webapi.model.output.EntryOutputModel
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component

@Component
class EntryModelAssembler : RepresentationModelAssemblerSupport<EntryBO, EntryOutputModel>(
    EntryController::class.java, EntryOutputModel::class.java,
) {

    override fun toModel(entity: EntryBO): EntryOutputModel {

        return EntryOutputModel(
            userTag = entity.userTag,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        ).apply {
            this.add(
                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(EntryController::class.java).find(id = entity.id)
                ).withSelfRel()
            )
            add(
                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(EntryController::class.java).find(entity.id)
                ).withRel("entry: read")
            )
            add(
                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(EntryController::class.java).select()
                ).withRel("entry: select")
            )
            add(
                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(EntryController::class.java).delete(entity.id)
                ).withRel("entry: delete")
            )
            add(
                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(EntryController::class.java).update(entity.id)
                ).withRel("entry: update")
            )
        }
    }
}

