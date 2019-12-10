package org.lamisplus.modules.patient.extensions;

import com.foreach.across.core.annotations.ModuleConfiguration;
import com.foreach.across.modules.hibernate.jpa.AcrossHibernateJpaModule;
import com.foreach.across.modules.hibernate.jpa.repositories.config.EnableAcrossJpaRepositories;
import com.foreach.across.modules.hibernate.provider.HibernatePackageConfigurer;
import com.foreach.across.modules.hibernate.provider.HibernatePackageRegistry;
import lombok.extern.slf4j.Slf4j;
import org.fhi360.lamis.modules.base.domain.BaseDomain;
import org.lamisplus.modules.patient.domain.model.ModelScan;
import org.lamisplus.modules.patient.domain.repositories.RepositoryScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@ModuleConfiguration(AcrossHibernateJpaModule.NAME)
@Configuration
@EnableAcrossJpaRepositories(basePackageClasses = RepositoryScan.class)
@EnableElasticsearchRepositories(basePackages = "org.lamisplus.domain.repositories.search")
@Slf4j
public class EntityScanConfiguration implements HibernatePackageConfigurer {
    @Override
    public void configureHibernatePackage(HibernatePackageRegistry hibernatePackageRegistry) {
        hibernatePackageRegistry.addPackageToScan(ModelScan.class, BaseDomain.class);
    }
}
