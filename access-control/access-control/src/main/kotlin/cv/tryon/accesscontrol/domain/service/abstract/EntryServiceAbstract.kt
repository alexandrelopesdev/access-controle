package cv.tryon.accesscontrol.domain.service.abstract

import cv.tryon.accesscontrol.data.model.Entry
import cv.tryon.accesscontrol.data.reposotories.EntryRepository
import cv.tryon.accesscontrol.domain.adapters.EntryAdapter
import cv.tryon.accesscontrol.domain.service.AccessControlServices
import cv.tryon.accesscontrol.webapi.controlers.assembler.EntryModelAssembler
import cv.tryon.accesscontrol.webapi.model.inputs.EntryInputModel
import cv.tryon.accesscontrol.webapi.model.output.EntryOutputModel
import org.springframework.http.ResponseEntity

abstract class EntryServiceAbstract(
    private val repository: EntryRepository,
    private val entryModelAssembler: EntryModelAssembler
) : AccessControlServices<EntryInputModel, EntryOutputModel> {

    override fun createUpdate(input: EntryInputModel): ResponseEntity<EntryOutputModel> {

        val entry = Entry(
            userTag = input.userTag,
            createdAt = input.createdAt,
            updatedAt = input.updatedAt
        )

        val save = repository.save(entry)

        return ResponseEntity.ok(
            entryModelAssembler.toModel(
                EntryAdapter.toModel(save)
            )
        )

    }

    override fun find(id: String): ResponseEntity<EntryOutputModel> {

        val entry = repository.findById(id).orElseThrow()

        return ResponseEntity.ok(
            entryModelAssembler.toModel(
                EntryAdapter.toModel(entry)
            )
        )
    }

    override fun select(): MutableSet<EntryOutputModel> {

        val findAll = repository.findAll()

        return findAll.map { entry ->
            entryModelAssembler.toModel(
                EntryAdapter.toModel(entry)
            )
        }.toMutableSet()

    }

    override fun update(id: String): ResponseEntity<EntryOutputModel> {
        val entry = repository.findById(id).orElseThrow()

        entry.apply {
            this.updatedAt = "Alexandre"
        }

        return ResponseEntity.ok(
            entryModelAssembler.toModel(
                EntryAdapter.toModel(repository.save(entry))
            )
        )
    }

    override fun delete(id: String): ResponseEntity<Boolean> {

        val entry = repository.findById(id).orElseThrow()

        repository.deleteById(id)

        return ResponseEntity.ok(true)
    }
}