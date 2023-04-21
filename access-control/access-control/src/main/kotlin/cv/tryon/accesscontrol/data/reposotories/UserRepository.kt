package cv.tryon.accesscontrol.data.reposotories

import cv.tryon.accesscontrol.data.model.UserAccessControl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface UserRepository : JpaRepository<UserAccessControl, String>, JpaSpecificationExecutor<UserAccessControl>

