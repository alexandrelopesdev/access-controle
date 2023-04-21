package cv.tryon.accesscontrol.data.model

import cv.tryon.accesscontrol.constant.Constant
import jakarta.persistence.Embeddable
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator

@Embeddable
class Contact(
    var phoneNumber: String = Constant.defaultString,
    var adrres: String = Constant.defaultString,
    var email: String = Constant.defaultString,
)
