package cv.tryon.accesscontrol.webapi.controlers.assembler

import cv.tryon.accesscontrol.domain.model.UserBO
import cv.tryon.accesscontrol.webapi.controlers.UserController
import cv.tryon.accesscontrol.webapi.model.output.UserOutputModel
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component

@Component
class UserModelAssembler : RepresentationModelAssemblerSupport<UserBO, UserOutputModel>(
    UserController::class.java, UserOutputModel::class.java,
) {

    override fun toModel(entity: UserBO): UserOutputModel {

        return UserOutputModel(
            name = entity.name,
            role = entity.role,
            company = entity.company,
        ).apply {
            this.add(
                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(UserController::class.java).find(id = entity.id)
                ).withSelfRel()
            )
            add(
                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(UserController::class.java).delete(entity.id)
                ).withRel("user: delete  ")
            )
            add(
                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(UserController::class.java).update(entity.id)
                ).withRel("user: update  ")
            )
            add(
                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(UserController::class.java).select()
                ).withRel("user: select  ")
            )
        }
    }
}
