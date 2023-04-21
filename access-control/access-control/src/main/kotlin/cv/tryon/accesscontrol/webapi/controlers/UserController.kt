package cv.tryon.accesscontrol.webapi.controlers

import cv.tryon.accesscontrol.data.reposotories.UserRepository
import cv.tryon.accesscontrol.domain.service.abstract.UserServiceAbstract
import cv.tryon.accesscontrol.webapi.controlers.assembler.UserModelAssembler
import cv.tryon.accesscontrol.webapi.controlers.constant.ConstantAuthority
import cv.tryon.accesscontrol.webapi.controlers.constant.ConstantUrl
import cv.tryon.accesscontrol.webapi.model.inputs.UserInputModel
import cv.tryon.accesscontrol.webapi.model.output.UserOutputModel
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(ConstantUrl.START_USER)
class UserController(
    private val repository: UserRepository,
    private val assembler: UserModelAssembler
) : UserServiceAbstract(repository = repository, userModelAssembler = assembler) {

    @PostMapping(ConstantUrl.CREATE)
    @PreAuthorize(ConstantAuthority.CREATE_USER)
    override fun createUpdate(@RequestBody input: UserInputModel): ResponseEntity<UserOutputModel> {
        return super.createUpdate(input)
    }

    @GetMapping(ConstantUrl.FIND)
    @PreAuthorize(ConstantAuthority.FIND_USER)
    override fun find(@PathVariable id: String): ResponseEntity<UserOutputModel> {
        return super.find(id)
    }


    @GetMapping(ConstantUrl.SELECT)
    @PreAuthorize(ConstantAuthority.SELECT_USER)
    override fun select(): MutableSet<UserOutputModel> {
        return super.select()
    }

    @PutMapping(ConstantUrl.DELETE)
    @PreAuthorize(ConstantAuthority.DELETE_USER)
    override fun delete(@PathVariable id: String): ResponseEntity<Boolean> {
        return super.delete(id)
    }

    @PutMapping(ConstantUrl.UPDATE)
    @PreAuthorize(ConstantAuthority.UPDATE_USER)
    override fun update(@PathVariable id: String): ResponseEntity<UserOutputModel> {
        return super.update(id)
    }
}

