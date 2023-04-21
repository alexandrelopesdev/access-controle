package cv.tryon.accesscontrol.domain.service.abstract

import cv.tryon.accesscontrol.data.model.Device
import cv.tryon.accesscontrol.data.reposotories.DeviceRepository
import cv.tryon.accesscontrol.domain.adapters.DeviceAdapter
import cv.tryon.accesscontrol.domain.model.DeviceBO
import cv.tryon.accesscontrol.domain.service.AccessControlServices
import cv.tryon.accesscontrol.webapi.controlers.assembler.DeviceModelAssembler
import cv.tryon.accesscontrol.webapi.model.inputs.DeviceInputModel
import cv.tryon.accesscontrol.webapi.model.output.DeviceOutputModel
import org.springframework.http.ResponseEntity

abstract class DeviceServiceAbstract(
    private val repository: DeviceRepository,
    private val deviceModelAssembler: DeviceModelAssembler
) : AccessControlServices<DeviceInputModel, DeviceOutputModel> {

    override fun createUpdate(inputModel: DeviceInputModel): ResponseEntity<DeviceOutputModel> {

        val device = Device(
            macAddress = inputModel.macAddress,
            name = inputModel.name,
            model = inputModel.model,

            )
        val save = repository.save(device)

        println("______________________________________")
        println("Deivice Id: ${save.id}")
        println("______________________________________")

        val entity = DeviceBO(
            id = save.id,
            macAddress = save.macAddress,
            name = save.name,
            model = save.model
        )

        println("________________________________________________________")
        println("Device = ${entity.id}")
        println("________________________________________________________")

        val toModel = deviceModelAssembler.toModel(
            entity
        )

        println("________________________________________________________")
        println("toModel = ${toModel}")
        println("________________________________________________________")
        return ResponseEntity.ok(toModel)
    }

    override fun find(id: String): ResponseEntity<DeviceOutputModel> {
        val findById = repository.findById(id).orElseThrow()

        return ResponseEntity.ok(
            deviceModelAssembler.toModel(
                DeviceAdapter.toModel(findById)
            )
        )
    }

    override fun select(): MutableSet<DeviceOutputModel> {
        val findAll = repository.findAll()

        return findAll.map {
            deviceModelAssembler.toModel(
                DeviceAdapter.toModel(it)
            )
        }.toMutableSet()
    }

    override fun update(id: String): ResponseEntity<DeviceOutputModel> {

        val findById = repository.findById(id).orElseThrow()

        val device = findById.apply {
            this.model = "Alexandre"
        }

        return ResponseEntity.ok(
            deviceModelAssembler.toModel(
                DeviceAdapter.toModel(repository.save(device))
            )
        )
    }

    override fun delete(id: String): ResponseEntity<Boolean> {

        val findById = repository.findById(id).orElseThrow()

        repository.deleteById(id)

        return ResponseEntity.ok(true)
    }
}
