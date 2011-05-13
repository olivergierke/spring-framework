/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.orm.jpa.eclipselink;

import org.eclipse.persistence.jpa.JpaEntityManager;
import org.springframework.orm.jpa.AbstractContainerEntityManagerFactoryIntegrationTests;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;

/**
 * EclipseLink-specific JPA tests using the {@link EntityManagerFactoryBuilder} API.
 *
 * @author Chris Beams
 * @since 3.1
 */
public class EclipseLinkEntityManagerFactoryBuilderIntegrationTests extends AbstractContainerEntityManagerFactoryIntegrationTests {

	public static final String[] ECLIPSELINK_CONFIG_BUILDER_LOCATIONS = new String[] {
			"/org/springframework/orm/jpa/eclipselink/eclipselink-manager-builder.xml",
			"/org/springframework/orm/jpa/memdb.xml",
			"/org/springframework/orm/jpa/inject.xml"};

	protected String[] getConfigLocations() {
		return ECLIPSELINK_CONFIG_BUILDER_LOCATIONS;
	}

	public void testCanCastNativeEntityManagerFactoryToTopLinkEntityManagerFactoryImpl() {
		EntityManagerFactoryInfo emfi = (EntityManagerFactoryInfo) entityManagerFactory;
		assertTrue(emfi.getNativeEntityManagerFactory().getClass().getName().endsWith("EntityManagerFactoryImpl"));
	}

	public void testCanCastSharedEntityManagerProxyToTopLinkEntityManager() {
		assertTrue(sharedEntityManager instanceof JpaEntityManager);
		JpaEntityManager eclipselinkEntityManager = (JpaEntityManager) sharedEntityManager;
		assertNotNull(eclipselinkEntityManager.getActiveSession());
	}
}
