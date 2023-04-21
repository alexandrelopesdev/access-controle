package cv.tryon.accesscontrol.data.model

import cv.tryon.accesscontrol.constant.Constant
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
class Entry(
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid") var id: String = "",

    var userTag: String = Constant.defaultString,
    var createdAt: String = Constant.defaultString,
    var updatedAt: String = Constant.defaultString,

    @OneToMany(mappedBy = "entry", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @Column(nullable = true)
    var device: MutableSet<Device> = mutableSetOf()
)

