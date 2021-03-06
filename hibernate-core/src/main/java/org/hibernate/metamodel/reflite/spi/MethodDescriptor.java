/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2014, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.metamodel.reflite.spi;

import java.util.Collection;

/**
 * Describes a method in a java type
 *
 * @author Steve Ebersole
 */
public interface MethodDescriptor {
	/**
	 * The name of the method
	 *
	 * @return The name of the method
	 */
	public String getName();

	/**
	 * The type declaring this method
	 *
	 * @return The type declaring this method
	 */
	public JavaTypeDescriptor getDeclaringType();

	/**
	 * The modifiers for this method.  See {@link java.lang.reflect.Modifier}
	 *
	 * @return The modifiers for this method
	 */
	public int getModifiers();

	/**
	 * The descriptor of the method's return type
	 *
	 * @return The descriptor of the method's return type
	 */
	public JavaTypeDescriptor getReturnType();

	/**
	 * The descriptor of the method's argument types
	 *
	 * @return The descriptor of the method's argument types
	 */
	public Collection<JavaTypeDescriptor> getParameterTypes();
}
