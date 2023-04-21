package cv.tryon.accesscontrol.data.reposotories

import cv.tryon.accesscontrol.data.model.Device
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface DeviceRepository : JpaRepository<Device, String>, JpaSpecificationExecutor<Device>