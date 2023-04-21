package cv.tryon.accesscontrol.webapi.controlers

import cv.tryon.accesscontrol.data.reposotories.EntryRepository
import cv.tryon.accesscontrol.domain.service.abstract.EntryServiceAbstract
import cv.tryon.accesscontrol.webapi.controlers.assembler.EntryModelAssembler
import cv.tryon.accesscontrol.webapi.controlers.constant.ConstantAuthority
import cv.tryon.accesscontrol.webapi.controlers.constant.ConstantUrl
import cv.tryon.accesscontrol.webapi.model.inputs.EntryInputModel
import cv.tryon.accesscontrol.webapi.model.output.EntryOutputModel
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
@RequestMapping(ConstantUrl.START_ENTRY)
class EntryController(
    private val repository: EntryRepository,
    private val assembler: EntryModelAssembler

) : EntryServiceAbstract(repository = repository, entryModelAssembler = assembler) {

    @PostMapping(ConstantUrl.CREATE)
    @PreAuthorize(ConstantAuthority.CREATE_ENTRY)
    override fun createUpdate(@RequestBody input: EntryInputModel): ResponseEntity<EntryOutputModel> {
        return super.createUpdate(input)
    }

    @GetMapping(ConstantUrl.FIND)
    @PreAuthorize(ConstantAuthority.FIND_ENTRY)
    override fun find(@PathVariable id: String): ResponseEntity<EntryOutputModel> {
        return super.find(id)
    }


    @GetMapping(ConstantUrl.SELECT)
    @PreAuthorize(ConstantAuthority.SELECT_ENTRY)
    override fun select(): MutableSet<EntryOutputModel> {
        return super.select()
    }

    @PutMapping(ConstantUrl.DELETE)
    @PreAuthorize(ConstantAuthority.DELETE_ENTRY)
    override fun delete(@PathVariable id: String): ResponseEntity<Boolean> {
        return super.delete(id)
    }

    @PutMapping(ConstantUrl.UPDATE)
    @PreAuthorize(ConstantAuthority.UPDATE_ENTRY)
    override fun update(@PathVariable id: String): ResponseEntity<EntryOutputModel> {
        return super.update(id)
    }

}
