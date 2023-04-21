package cv.tryon.accesscontrol.data.reposotories

import cv.tryon.accesscontrol.data.model.Entry
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface EntryRepository : JpaRepository<Entry, String>, JpaSpecificationExecutor<Entry>