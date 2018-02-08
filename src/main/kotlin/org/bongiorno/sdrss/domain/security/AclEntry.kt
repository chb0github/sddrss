package org.bongiorno.sdrss.domain.security

import org.springframework.hateoas.Identifiable
import java.util.stream.Stream
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "acl_entry")
class AclEntry : Identifiable<Long> {

    @Id
    @GeneratedValue
    private val id: Long? = null

    @NotNull
    @OneToOne
    private val aclObjectIdentity: AclObjectIdentity

    @NotNull
    val aceOrder: Int? = null

    @NotNull
    @OneToOne
    val sid: AclSid

    @NotNull
    val mask: Int?


    @NotNull
    val granting: Boolean? = null

    @NotNull
    val auditSuccess: Boolean? = null

    @NotNull
    val auditFailure: Boolean? = null

    constructor(aclObjectIdentity: AclObjectIdentity, sid: AclSid, vararg permissions: Permission) {
        this.aclObjectIdentity = aclObjectIdentity
        this.sid = sid
        this.mask = Stream.of(*permissions).map<Int>(Function<Permission, Int> { it.ordinal }).reduce { a, b -> a or b }.orElse(0)
    }

    enum class Permission {
        NONE,
        READ,
        WRITE
    }
}
