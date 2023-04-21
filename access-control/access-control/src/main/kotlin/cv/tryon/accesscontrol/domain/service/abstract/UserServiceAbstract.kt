package cv.tryon.accesscontrol.domain.service.abstract

import cv.tryon.accesscontrol.data.model.UserAccessControl
import cv.tryon.accesscontrol.data.reposotories.UserRepository
import cv.tryon.accesscontrol.domain.adapters.UserAdapter
import cv.tryon.accesscontrol.domain.service.AccessControlServices
import cv.tryon.accesscontrol.webapi.controlers.assembler.UserModelAssembler
import cv.tryon.accesscontrol.webapi.model.inputs.UserInputModel
import cv.tryon.accesscontrol.webapi.model.output.UserOutputModel
import org.springframework.http.ResponseEntity

abstract class UserServiceAbstract(
    private val repository: UserRepository,
    private val userModelAssembler: UserModelAssembler
) : AccessControlServices<UserInputModel, UserOutputModel> {

    override fun createUpdate(input: UserInputModel): ResponseEntity<UserOutputModel> {
        val user = UserAccessControl(
            name = input.name,
            role = input.role,
            company = input.company
        )
        val save = repository.save(user)

        return ResponseEntity.ok(
            userModelAssembler.toModel(
                UserAdapter.toModel(save)
            )
        )
    }

    override fun find(id: String): ResponseEntity<UserOutputModel> {

        val findById = repository.findById(id).orElseThrow()

        return ResponseEntity.ok(
            userModelAssembler.toModel(
                UserAdapter.toModel(findById)
            )
        )
    }

    override fun select(): MutableSet<UserOutputModel> {
        val finAll = repository.findAll()

        return finAll.map { user ->
            userModelAssembler.toModel(
                UserAdapter.toModel(user)
            )
        }.toMutableSet()
    }

    override fun update(id: String): ResponseEntity<UserOutputModel> {
        val findById = repository.findById(id).orElseThrow()

        findById.apply {
            this.company = "Alexandre"
        }

        return ResponseEntity.ok(
            userModelAssembler.toModel(
                UserAdapter.toModel(
                    repository.save(findById)
                )
            )
        )
    }

    override fun delete(id: String): ResponseEntity<Boolean> {
        val findById = repository.findById(id).orElseThrow()

        repository.deleteById(id)

        return ResponseEntity.ok(true)
    }
}