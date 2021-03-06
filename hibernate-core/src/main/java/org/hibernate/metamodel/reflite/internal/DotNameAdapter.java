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
package org.hibernate.metamodel.reflite.internal;

import org.hibernate.metamodel.reflite.spi.Name;

import org.jboss.jandex.DotName;

/**
 * {@link Name} adapter for Janxdex {@link DotName}
 *
 * @author Steve Ebersole
 */
class DotNameAdapter implements Name {
	private final DotName dotName;

	DotNameAdapter(String name) {
		final int loc = name.lastIndexOf( '.' );
		if ( loc < 0 ) {
			this.dotName = DotName.createSimple( name );
		}
		else {
			this.dotName = DotName.createComponentized(
					DotName.createSimple( name.substring( 0, loc ) ),
					name.substring( loc + 1 )
			);
		}
	}

	@Override
	public String getQualifier() {
		return dotName.prefix() == null ? null : dotName.prefix().toString();
	}

	@Override
	public String getUnqualifiedName() {
		return dotName.local();
	}

	@Override
	public String fullName() {
		return dotName.toString();
	}

	@Override
	public String toString() {
		return "DotNameAdapter{dotName=" + dotName.toString() + '}';
	}

	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		final DotNameAdapter that = (DotNameAdapter) o;
		return dotName.equals( that.dotName );
	}

	@Override
	public int hashCode() {
		return dotName.hashCode();
	}
}
