package com.library.the_archive;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link TheArchiveApplication}.
 */
@Generated
public class TheArchiveApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'theArchiveApplication'.
   */
  public static BeanDefinition getTheArchiveApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TheArchiveApplication.class);
    beanDefinition.setInstanceSupplier(TheArchiveApplication::new);
    return beanDefinition;
  }
}
