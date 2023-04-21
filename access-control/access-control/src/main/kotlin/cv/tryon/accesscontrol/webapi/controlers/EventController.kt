package cv.tryon.accesscontrol.webapi.controlers

import com.sun.java.accessibility.util.EventID
import cv.tryon.accesscontrol.data.reposotories.EventRepository
import cv.tryon.accesscontrol.domain.service.abstract.EventServiceAbstract
import cv.tryon.accesscontrol.webapi.controlers.assembler.EventModelAssembler
import cv.tryon.accesscontrol.webapi.controlers.constant.ConstantAuthority
import cv.tryon.accesscontrol.webapi.controlers.constant.ConstantUrl
import cv.tryon.accesscontrol.webapi.model.inputs.EventInputModel
import cv.tryon.accesscontrol.webapi.model.output.EventOutputModel
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

@RequestMapping(ConstantUrl.START_EVENT)
class EventController(
    private val repository: EventRepository,
    private val assembler: EventModelAssembler

) : EventServiceAbstract(repository = repository, eventModelAssembler = assembler) {

    @PostMapping(ConstantUrl.CREATE)
    @PreAuthorize(ConstantAuthority.CREATE_EVENT)
    override fun createUpdate(@RequestBody input: EventInputModel): ResponseEntity<EventOutputModel> {
        return super.createUpdate(input)
    }

    @GetMapping(ConstantUrl.FIND)
    @PreAuthorize(ConstantAuthority.FIND_EVENT)
    override fun find(@PathVariable id: String): ResponseEntity<EventOutputModel> {
        return super.find(id)
    }


    @GetMapping(ConstantUrl.SELECT)
    @PreAuthorize(ConstantAuthority.SELECT_EVENT)
    override fun select(): MutableSet<EventOutputModel> {
        return super.select()
    }

    @PutMapping(ConstantUrl.UPDATE)
    @PreAuthorize(ConstantAuthority.UPDATE_EVENT)
    override fun update(@PathVariable id: String): ResponseEntity<EventOutputModel> {
        return super.update(id)
    }

    @PutMapping(ConstantUrl.DELETE)
    @PreAuthorize(ConstantAuthority.DELETE_EVENT)
    override fun delete(@PathVariable id: String): ResponseEntity<Boolean> {
        return super.delete(id)
    }

    @GetMapping(ConstantUrl.VALID_TAG)
    @PreAuthorize(ConstantAuthority.VALID_tag)
    override fun valid(@PathVariable tag: String, @PathVariable eventId: String): ResponseEntity<Boolean> {
        return super.valid(tag, eventId)

    }
}