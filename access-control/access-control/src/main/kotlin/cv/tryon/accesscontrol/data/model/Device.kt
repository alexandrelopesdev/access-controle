package cv.tryon.accesscontrol.data.model

import cv.tryon.accesscontrol.constant.Constant
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator


@Entity
class Device(
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid") var id: String = Constant.defaultString,

    @Column(unique = true)
    var macAddress: String = Constant.defaultString,

    var name: String = Constant.defaultString,
    var model: String = Constant.defaultString,

    @OneToMany(mappedBy = "device")
    var accessControlUser: MutableSet<UserAccessControl> = mutableSetOf(),

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
    @JoinColumn(name = "entry_id", nullable = true)
    var entry: Entry? = null
)

