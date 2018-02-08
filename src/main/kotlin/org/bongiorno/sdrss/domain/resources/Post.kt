package org.bongiorno.sdrss.domain.resources


import java.util.Date
import org.springframework.hateoas.Identifiable

interface Post : Identifiable<Long> {

    val date: Date

    val name: String
}
