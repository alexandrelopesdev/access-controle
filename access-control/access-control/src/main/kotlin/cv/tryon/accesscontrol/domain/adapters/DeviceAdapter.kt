package cv.tryon.accesscontrol.domain.adapters

import cv.tryon.accesscontrol.data.model.Device
import cv.tryon.accesscontrol.domain.model.DeviceBO

class DeviceAdapter {

    companion object {

        fun toModel(device: Device) = DeviceBO(
            id = device.id,
            macAddress = device.macAddress,
            name = device.name,
            model = device.model,
        )

        fun toModel(device: DeviceBO) = Device(
            id = device.id,
            macAddress = device.macAddress,
            name = device.name,
            model = device.model,
        )
    }
}
