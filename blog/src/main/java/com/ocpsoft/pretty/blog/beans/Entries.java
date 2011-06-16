/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocpsoft.pretty.blog.beans;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.jboss.forge.persistence.PersistenceUtil;
import org.jboss.seam.transaction.Transactional;

import com.ocpsoft.pretty.blog.domain.Author;
import com.ocpsoft.pretty.blog.domain.Content;

/**
 * 
 * @author lbaxter
 */
@Named
@RequestScoped
@Transactional
public class Entries extends PersistenceUtil
{
   private static final long serialVersionUID = -6786144542865153483L;

   private Content content = new Content();

   @Inject
   private EntityManager manager;

   private List<Content> entries;

   private Content singleContent;

   @Inject
   private ParamsBean paramsBean;

   @Override
   protected EntityManager getEntityManager()
   {
      return manager;
   }

   public String newEntry(final Author author, final Content content)
   {
      content.setPostedOn(new Date());
      content.setAuthor(author);
      save(content);
      author.getPublishedContent().add(content);
      save(author);

      return "/single.xhtml?faces-redirect=true&post=" + content.getTitle();
   }

   public List<Content> getIndexContent()
   {
      if (entries == null)
      {
         entries = findAll(Content.class);
         Collections.sort(entries);
         Collections.reverse(entries);
      }
      return entries;
   }

   public Content getSingleContent()
   {
      try
      {
         if (singleContent == null)
            singleContent = findUniqueByNamedQuery("content.byTitle", paramsBean.getTitle());
      }
      catch (NoResultException e)
      {
         return new Content();
      }

      return singleContent;
   }

   public Content getContent()
   {
      return content;
   }

   public void setContent(final Content content)
   {
      this.content = content;
   }

}
