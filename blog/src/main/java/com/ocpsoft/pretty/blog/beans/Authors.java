/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocpsoft.pretty.blog.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.forge.persistence.PersistenceUtil;
import org.jboss.seam.international.status.Messages;
import org.jboss.seam.transaction.Transactional;

import com.ocpsoft.pretty.blog.domain.Author;

/**
 * 
 * @author lbaxter
 */
@Named
@RequestScoped
@Transactional
public class Authors extends PersistenceUtil
{
   private static final long serialVersionUID = -6786144542865153483L;

   @Inject
   private Messages messages;

   @Inject
   private EntityManager manager;

   private Author author = new Author();

   private List<Author> authors;

   @Override
   protected EntityManager getEntityManager()
   {
      return manager;
   }

   public String newAuthor(final Author author)
   {
      save(author);
      return "/authors.xhtml?faces-redirect=true";
   }

   public List<Author> getAll()
   {
      if (authors == null)
         authors = findAll(Author.class);

      return authors;
   }

   public Author getAuthor()
   {
      return author;
   }

   public void setAuthor(final Author author)
   {
      this.author = author;
   }
}
