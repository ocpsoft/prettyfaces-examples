/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc., and individual contributors
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
package com.ocpsoft.pretty.blog.beans;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;

import org.jboss.forge.persistence.PersistenceUtil;
import org.jboss.seam.transaction.Transactional;

import com.ocpsoft.pretty.blog.domain.Author;
import com.ocpsoft.pretty.blog.domain.Content;

/**
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 * 
 */

@Transactional
// TODO @Veto this bean when actually deploying.
public class BlogStartup extends PersistenceUtil
{
   private static final long serialVersionUID = 2529224352539074715L;

   @Inject
   private EntityManager manager;

   public void init(final ServletContext event)
   {
      Author author = new Author("Lincoln Baxter, III");
      Content post = new Content();

      post.setAuthor(author);
      post.setTitle("My first PrettyFaces based blog entry!");
      post.setBody("Once upon a time, there was a man named Vlat."
               + "<p/>His timultuous past shone fiercly upon his thorn-touched face, "
               + "but it was not every day that he got the chance to see his daughter. "
               + "Not out of obligation or dysfunction, but purely a rift caused by " + "policies of the time."
               + "<p/>She was a striking woman, about 5'11\" tall, with a golden braid "
               + "falling symmetrically between her shoulders.");

      save(author);
      save(post);

      System.out.println("Initializing database.");
      manager.flush();
   }

   @Override
   protected EntityManager getEntityManager()
   {
      return manager;
   }

}
