package cv.tryon.accesscontrol.data.reposotories

import cv.tryon.accesscontrol.data.model.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface EventRepository : JpaRepository<Event, String>, JpaSpecificationExecutor<Event>
