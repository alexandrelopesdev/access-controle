package cv.tryon.accesscontrol.domain.adapters

import cv.tryon.accesscontrol.data.model.UserAccessControl
import cv.tryon.accesscontrol.domain.model.UserBO

class UserAdapter {

    companion object {
       
        fun toModel(user: UserAccessControl) = UserBO(
            id = user.id,
           name = user.name,
            role = user.role,
            company = user.company
        )
               
        fun toModel(user: UserBO) = UserAccessControl(
            name = user.name,
            role = user.role,
            company = user.company
        )
    }
}
