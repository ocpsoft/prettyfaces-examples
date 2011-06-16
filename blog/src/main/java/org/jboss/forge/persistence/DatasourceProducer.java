package org.jboss.forge.persistence;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jboss.seam.solder.core.ExtensionManaged;

/**
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 */
public class DatasourceProducer
{
   @ExtensionManaged
   @Produces
   @PersistenceUnit
   private EntityManagerFactory emf;
}
