package cv.tryon.accesscontrol.data.model

import cv.tryon.accesscontrol.constant.Constant
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator

@Entity
class Event(
    @Id val id: String = Constant.defaultString,

    var sellerName: String = Constant.defaultString,
    val startDate: String = Constant.defaultString,
    val endDate: String = Constant.defaultString,

    var tag: MutableSet<String> = mutableSetOf(),
)