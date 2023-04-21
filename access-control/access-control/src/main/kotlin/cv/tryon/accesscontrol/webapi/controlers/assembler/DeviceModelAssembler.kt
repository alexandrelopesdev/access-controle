package cv.tryon.accesscontrol.webapi.controlers.assembler

import cv.tryon.accesscontrol.domain.model.DeviceBO
import cv.tryon.accesscontrol.webapi.controlers.DeviceController
import cv.tryon.accesscontrol.webapi.model.output.DeviceOutputModel
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component

@Component
class DeviceModelAssembler : RepresentationModelAssemblerSupport<DeviceBO, DeviceOutputModel>(
    DeviceController::class.java, DeviceOutputModel::class.java,
) {

    override fun toModel(entity: DeviceBO): DeviceOutputModel {

        println("________________________________________________________")
        println("DeviceBo = ${entity.id}")
        println("________________________________________________________")

        return DeviceOutputModel(
            macAddress = entity.macAddress,
            model = entity.model,
            name = entity.name,

            ).apply {
            this.add(
                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(DeviceController::class.java).find(entity.id)
                ).withSelfRel()
            )
            add(

                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(DeviceController::class.java).delete(entity.id)
                ).withRel("device: delete")
            )
            add(

                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(DeviceController::class.java).update(entity.id)
                ).withRel("device: update")
            )
            add(

                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(DeviceController::class.java).select()
                ).withRel("device: select")
            )
        }
    }
}
