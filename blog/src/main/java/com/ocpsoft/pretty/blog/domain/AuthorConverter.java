/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package com.ocpsoft.pretty.blog.domain;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.forge.persistence.PersistenceUtil;

/**
 * @author <a href="mailto:lincolnbaxter@gmail.com>Lincoln Baxter, III</a>
 * 
 */
@RequestScoped
@FacesConverter(forClass = Author.class)
public class AuthorConverter extends PersistenceUtil implements Converter, Serializable
{
   private static final long serialVersionUID = -6715797808010578145L;

   @Inject
   EntityManager manager;

   @Override
   public Object getAsObject(final FacesContext context, final UIComponent comp, final String name)
   {
      Author result = null;

      if (name != null)
      {
         result = findUniqueByNamedQuery("author.byName", name);
      }

      return result;
   }

   @Override
   public String getAsString(final FacesContext context, final UIComponent comp, final Object author)
   {
      if (author instanceof Author)
      {
         return ((Author) author).getName();
      }

      return "";
   }

   @Override
   protected EntityManager getEntityManager()
   {
      return manager;
   }

}
