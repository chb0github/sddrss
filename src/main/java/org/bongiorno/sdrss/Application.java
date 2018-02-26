package org.bongiorno.sdrss;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.validation.Validator;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    @RequiredArgsConstructor
    public static class ValidationConfiguration extends RepositoryRestConfigurerAdapter {

        private final Validator jsr303Validator;

        @Override
        public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
            //bean validation always before save and create
            validatingListener.addValidator("beforeCreate", jsr303Validator);
            validatingListener.addValidator("beforeSave", jsr303Validator);

        }


    }

    @Bean
    @Profile("default")
    public DataSource dataSource() {
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }


}
