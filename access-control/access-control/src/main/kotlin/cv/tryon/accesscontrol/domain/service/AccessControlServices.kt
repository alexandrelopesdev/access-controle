package cv.tryon.accesscontrol.domain.service

import org.springframework.http.ResponseEntity

interface AccessControlServices<Input, Output> {

    fun createUpdate(input: Input): ResponseEntity<Output>

    fun find(id: String): ResponseEntity<Output>

    fun select(): MutableSet<Output>

    fun update(id: String): ResponseEntity<Output>

    fun delete(id: String): ResponseEntity<Boolean>
}
