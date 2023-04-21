package cv.tryon.accesscontrol.webapi.controlers

import cv.tryon.accesscontrol.data.reposotories.DeviceRepository
import cv.tryon.accesscontrol.domain.service.abstract.DeviceServiceAbstract
import cv.tryon.accesscontrol.webapi.controlers.assembler.DeviceModelAssembler
import cv.tryon.accesscontrol.webapi.controlers.constant.ConstantAuthority
import cv.tryon.accesscontrol.webapi.controlers.constant.ConstantUrl
import cv.tryon.accesscontrol.webapi.model.inputs.DeviceInputModel
import cv.tryon.accesscontrol.webapi.model.output.DeviceOutputModel
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController

@RequestMapping(ConstantUrl.START_DEVICE)
class DeviceController(
    private val repository: DeviceRepository,
    private val deviceModelAssembler: DeviceModelAssembler

) : DeviceServiceAbstract(repository = repository, deviceModelAssembler = deviceModelAssembler) {

    @PostMapping(ConstantUrl.CREATE)
    @PreAuthorize(ConstantAuthority.CREATE_DEVICE)
    override fun createUpdate(@RequestBody inputModel: DeviceInputModel): ResponseEntity<DeviceOutputModel> {
        return super.createUpdate(inputModel)
    }

    @GetMapping(ConstantUrl.FIND)
    @PreAuthorize(ConstantAuthority.FIND_DEVICE)
    override fun find(@PathVariable id: String): ResponseEntity<DeviceOutputModel> {
        return super.find(id)
    }

    @GetMapping(ConstantUrl.SELECT)
    @PreAuthorize(ConstantAuthority.SELECT_DEVICE)
    override fun select(): MutableSet<DeviceOutputModel> {
        return super.select()
    }

    @PutMapping(ConstantUrl.DELETE)
    @PreAuthorize(ConstantAuthority.DELETE_DEVICE)
    override fun delete(@PathVariable id: String): ResponseEntity<Boolean> {
        return super.delete(id)
    }

    @PutMapping(ConstantUrl.UPDATE)
    @PreAuthorize(ConstantAuthority.UPDATE_DEVICE)
    override fun update(@PathVariable id: String): ResponseEntity<DeviceOutputModel> {
        return super.update(id)
    }

}
