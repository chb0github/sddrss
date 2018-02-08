package org.bongiorno.sdrss.domain.resources


import java.util.Date
import org.springframework.hateoas.Identifiable

interface Post  {

    var id:Long?

    var date: Date?

    var name: String?
}
