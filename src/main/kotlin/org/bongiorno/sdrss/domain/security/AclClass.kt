package org.bongiorno.sdrss.domain.security

import org.springframework.hateoas.Identifiable
import javax.persistence.*

@Entity
@Table(name = "acl_class")
data class AclClass(@Column(name = "class") val clazz: Class<*>,
                    @Id @GeneratedValue private val id: Long? = null) : Identifiable<Long> {
    override fun getId(): Long = id!!


    override fun toString(): String = clazz.name
}
