package com.ge.Hackathon.db.model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by JasonGibson on 9/17/16.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.ge.Hackathon.db.model"})
@EnableJpaRepositories(basePackages = {"com.ge.Hackathon.db.model"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
