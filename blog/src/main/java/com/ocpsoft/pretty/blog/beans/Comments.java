/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocpsoft.pretty.blog.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.forge.persistence.PersistenceUtil;
import org.jboss.seam.international.status.Messages;
import org.jboss.seam.international.status.builder.BundleKey;
import org.jboss.seam.transaction.Transactional;

import com.ocpsoft.pretty.blog.domain.Comment;
import com.ocpsoft.pretty.blog.domain.Content;

/**
 * 
 * @author lbaxter
 */
@Named
@RequestScoped
@Transactional
public class Comments extends PersistenceUtil
{
   private static final long serialVersionUID = -6786144542865153483L;

   private Comment comment = new Comment();

   @Inject
   private Messages messages;

   @Inject
   private EntityManager manager;

   @Override
   protected EntityManager getEntityManager()
   {
      return manager;
   }

   public Comments()
   {
   }

   public String addComment(final Content content, final Comment comment)
   {
      comment.setContent(content);
      save(comment);
      content.getComments().add(comment);
      save(content);

      messages.add(messages.info(new BundleKey("com.ocpsoft.pretty.blog.messages", "commentAdded"), content.getTitle()));

      return "/single.xhtml?faces-redirect=true&post=" + content.getTitle();
   }

   public List<Comment> getAll(final Content content)
   {
      return new ArrayList<Comment>(content.getComments());
   }

   public Comment getComment()
   {
      return comment;
   }

   public void setComment(final Comment comment)
   {
      this.comment = comment;
   }

}
