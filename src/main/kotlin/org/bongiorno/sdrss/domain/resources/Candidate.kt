package org.bongiorno.sdrss.domain.resources

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Candidate(@Id @GeneratedValue val id: Long,
                     override val date: Date,
                     override val name:String) : Post {
    override fun getId(): Long = id
}
