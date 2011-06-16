package com.ocpsoft.pretty.blog.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@NamedQueries({ @NamedQuery(name = "content.byTitle", query = "from Content c where c.title = ?") })
public class Content implements java.io.Serializable, Comparable<Content>
{
   @Id
   private @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   Long id = null;
   @Version
   private @Column(name = "version")
   int version = 0;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Column(unique = true, length = 128)
   private String title;

   public String getTitle()
   {
      return this.title;
   }

   public void setTitle(final String title)
   {
      this.title = title;
   }

   @Column(length = 100000)
   private String body;

   public String getBody()
   {
      return this.body;
   }

   public void setBody(final String body)
   {
      this.body = body;
   }

   private @Temporal(TemporalType.TIMESTAMP)
   Date postedOn;

   public Date getPostedOn()
   {
      return this.postedOn;
   }

   public void setPostedOn(final Date postedOn)
   {
      this.postedOn = postedOn;
   }

   @ManyToOne(optional = false)
   private Author author = new Author();

   public Author getAuthor()
   {
      return this.author;
   }

   public void setAuthor(final Author author)
   {
      this.author = author;
   }

   private @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
   Set<Comment> comments = new HashSet<Comment>();

   public Set<Comment> getComments()
   {
      return this.comments;
   }

   public void setComments(final Set<Comment> comments)
   {
      this.comments = comments;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((title == null) ? 0 : title.hashCode());
      return result;
   }

   @Override
   public boolean equals(final Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Content other = (Content) obj;
      if (title == null)
      {
         if (other.title != null)
            return false;
      }
      else if (!title.equals(other.title))
         return false;
      return true;
   }

   @Override
   public int compareTo(final Content o)
   {
      return postedOn.compareTo(o.postedOn);
   }
}
