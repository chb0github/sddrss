package org.bongiorno.sdrss.domain.resources

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Candidate(@Id @GeneratedValue override var id: Long? = null,
                     override var date: Date? = null,
                     override var name:String? = null) : Post