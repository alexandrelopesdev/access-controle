package cv.tryon.accesscontrol.data.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
class UserAccessControl(
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid") var id: String = "",

    var name: String = "",
    var role: String = "",
    var company: String = "",

    @Embedded
    var contact: Contact = Contact(),

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = true)
    var device: Device? = null
)

