/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2011, Red Hat Inc. or third-party contributors as
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
package org.hibernate.metamodel.internal.source.hbm;

import org.hibernate.AssertionFailure;
import org.hibernate.metamodel.source.internal.jaxb.hbm.JaxbArrayElement;
import org.hibernate.metamodel.source.internal.jaxb.hbm.JaxbListIndexElement;
import org.hibernate.metamodel.spi.source.AttributeSourceContainer;
import org.hibernate.metamodel.spi.source.AttributeSourceResolutionContext;
import org.hibernate.metamodel.spi.source.IndexedPluralAttributeSource;
import org.hibernate.metamodel.spi.source.PluralAttributeIndexSource;
import org.hibernate.metamodel.spi.source.SequentialPluralAttributeIndexSource;

/**
 * @author Brett Meyer
 */
public class ArraySourceImpl extends AbstractPluralAttributeSourceImpl implements IndexedPluralAttributeSource {

	private final SequentialPluralAttributeIndexSource indexSource;

	public ArraySourceImpl(
			MappingDocument sourceMappingDocument,
			JaxbArrayElement arrayElement,
			AttributeSourceContainer container) {
		super( sourceMappingDocument, arrayElement, container );
		JaxbListIndexElement listIndexElement = arrayElement.getListIndex();
		if ( listIndexElement == null ) {
			this.indexSource = new SequentialPluralAttributeIndexSourceImpl( sourceMappingDocument(), arrayElement.getIndex() );
		} else {
			this.indexSource = new SequentialPluralAttributeIndexSourceImpl( sourceMappingDocument(), listIndexElement );
		}
	}

	@Override
	public PluralAttributeIndexSource resolvePluralAttributeIndexSource(AttributeSourceResolutionContext context) {
		if ( indexSource == null ) {
			throw new AssertionFailure( "Array index source should have been resolved already." );
		}
		return indexSource;
	}

	@Override
	public PluralAttributeIndexSource getIndexSource() {
		return indexSource;
	}

	@Override
	public JaxbArrayElement getPluralAttributeElement() {
		return ( JaxbArrayElement ) super.getPluralAttributeElement();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.hibernate.metamodel.spi.source.PluralAttributeSource#getNature()
	 */
	@Override
	public Nature getNature() {
		return Nature.ARRAY;
	}
}
