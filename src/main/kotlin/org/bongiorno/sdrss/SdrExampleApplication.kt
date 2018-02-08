package org.bongiorno.sdrss

import org.bongiorno.sdrss.domain.resources.Candidate
import org.bongiorno.sdrss.domain.security.AclEntry
import org.bongiorno.sdrss.domain.security.AclEntry.Permission.READ
import org.bongiorno.sdrss.domain.security.AclEntry.Permission.WRITE
import org.bongiorno.sdrss.domain.security.AclObjectIdentity
import org.bongiorno.sdrss.domain.security.AclSid
import org.bongiorno.sdrss.repositories.security.AclEntryRepository
import org.bongiorno.sdrss.repositories.security.AclObjectIdentityRepository
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter
import org.springframework.hateoas.Identifiable
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.validation.Validator
import javax.sql.DataSource

@SpringBootApplication
@EnableAutoConfiguration
open class SdrExampleApplication {

    @Configuration
    open class ValidationConfiguration(private val jsr303Validator: Validator) : RepositoryRestConfigurerAdapter() {
        override fun configureValidatingRepositoryEventListener(validatingListener: ValidatingRepositoryEventListener?) {
            //bean validation always before save and create
            validatingListener!!.addValidator("beforeCreate", jsr303Validator)
            validatingListener.addValidator("beforeSave", jsr303Validator)

        }
    }

    @Bean
    open fun dataSource(): DataSource {

        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        return EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build()
    }

    @Component
    @RepositoryEventHandler
    open class CandidateEventHandler {
        // 2 different ways to handle events
        @HandleBeforeCreate
        fun handleBeforeCreate(c: Candidate) = println(" about to create candidate: $c")
    }

    // 2 different ways to handle events
    @Component
    open class PublishOnCreateEventListener(
            private val objects: AclObjectIdentityRepository? = null,
            private val aclEntries: AclEntryRepository? = null) :
            AbstractRepositoryEventListener<Identifiable<Long>>() {




        override fun onBeforeSave(entity: Identifiable<Long>?) {
            println("*********Saving " + entity!!)
        }

        override fun onAfterCreate(entity: Identifiable<Long>?) {
            System.out.format("*********Created %s%n", entity)
        }

        override fun onBeforeCreate(entity: Identifiable<Long>?) {
            println(entity)
        }

        override fun onAfterSave(entity: Identifiable<Long>?) {
            val authentication = SecurityContextHolder.getContext().authentication
            val aclSid = AclSid(true, authentication.principal.toString() + "")
            val objectEntry = objects!!.save(AclObjectIdentity(entity!!.javaClass, entity.id, aclSid))

            aclEntries!!.save(AclEntry(objectEntry, aclSid, READ, WRITE))
        }
    }
}


fun main(args: Array<String>) {
    SpringApplication.run(SdrExampleApplication::class.java, *args)
}

