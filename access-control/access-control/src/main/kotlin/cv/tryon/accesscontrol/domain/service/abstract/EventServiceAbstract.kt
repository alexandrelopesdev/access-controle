package cv.tryon.accesscontrol.domain.service.abstract

import cv.tryon.accesscontrol.data.model.Event
import cv.tryon.accesscontrol.data.reposotories.EventRepository
import cv.tryon.accesscontrol.domain.adapters.EventAdapter
import cv.tryon.accesscontrol.domain.service.AccessControlServices
import cv.tryon.accesscontrol.webapi.controlers.assembler.EventModelAssembler
import cv.tryon.accesscontrol.webapi.model.inputs.EventInputModel
import cv.tryon.accesscontrol.webapi.model.output.EventOutputModel
import org.springframework.http.ResponseEntity

abstract class EventServiceAbstract(
    private val repository: EventRepository,
    private val eventModelAssembler: EventModelAssembler
) : AccessControlServices<EventInputModel, EventOutputModel> {

    override fun createUpdate(input: EventInputModel): ResponseEntity<EventOutputModel> {


        if (repository.findById(input.id).isEmpty) {

            val event = Event(
                id = input.id,
                sellerName = input.sellerName,
                startDate = input.startDate,
                endDate = input.endDate,
                tag = mutableSetOf(input.tag)
            )
            return ResponseEntity.ok(
                eventModelAssembler.toModel(
                    EventAdapter.toModel(
                        repository.save(event)
                    )
                )
            )

        } else {

            val event = repository.findById(input.id).orElseThrow()

            event.apply {
                this.tag.add(input.tag)
            }

            return ResponseEntity.ok(
                eventModelAssembler.toModel(
                    EventAdapter.toModel(
                        repository.save(event)
                    )
                )
            )
        }


    }

    override fun find(id: String): ResponseEntity<EventOutputModel> {
        val event = repository.findById(id).orElseThrow()

        return ResponseEntity.ok(
            eventModelAssembler.toModel(
                EventAdapter.toModel(
                    repository.save(event)
                )
            )
        )
    }

    override fun select(): MutableSet<EventOutputModel> {
        val findAll = repository.findAll()

        return findAll.map { event ->
            eventModelAssembler.toModel(
                EventAdapter.toModel(
                    repository.save(event)
                )
            )
        }.toMutableSet()

    }

    override fun update(id: String): ResponseEntity<EventOutputModel> {
        val event = repository.findById(id).orElseThrow()

        event.apply {
            this.sellerName = "Alexandre"
        }

        return ResponseEntity.ok(
            eventModelAssembler.toModel(
                EventAdapter.toModel(
                    repository.save(event)
                )
            )
        )
    }

    override fun delete(id: String): ResponseEntity<Boolean> {
        val event = repository.findById(id).orElseThrow()

        repository.deleteById(id)

        return ResponseEntity.ok(
            true
        )
    }

    open fun valid(tag: String, eventId: String): ResponseEntity<Boolean> {

        val findById = repository.findById(eventId).orElseThrow()

        return ResponseEntity.ok(findById.tag.contains(tag))

    }
}