package com.ocpsoft.pretty.blog.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
public class Comment implements java.io.Serializable
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

   @Column(nullable = false)
   private String name;

   public String getName()
   {
      return this.name;
   }

   public void setName(final String name)
   {
      this.name = name;
   }

   @Column(nullable = false)
   private String email;

   public String getEmail()
   {
      return this.email;
   }

   public void setEmail(final String email)
   {
      this.email = email;
   }

   @Column
   private String url;

   public String getUrl()
   {
      return this.url;
   }

   public void setUrl(final String url)
   {
      this.url = url;
   }

   @Column(nullable = false)
   private String text;

   public String getText()
   {
      return this.text;
   }

   public void setText(final String text)
   {
      this.text = text;
   }

   @ManyToOne(optional = false)
   private Content content = new Content();

   public Content getContent()
   {
      return this.content;
   }

   public void setContent(final Content content)
   {
      this.content = content;
   }

   @Temporal(TemporalType.TIMESTAMP)
   @Column(nullable = false)
   private Date postedOn;

   public void setPostedOn(Date postedOn)
   {
      this.postedOn = postedOn;
   }

   public Date getPostedOn()
   {
      return postedOn;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((email == null) ? 0 : email.hashCode());
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      result = prime * result + ((text == null) ? 0 : text.hashCode());
      result = prime * result + ((url == null) ? 0 : url.hashCode());
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
      Comment other = (Comment) obj;
      if (email == null)
      {
         if (other.email != null)
            return false;
      }
      else if (!email.equals(other.email))
         return false;
      if (name == null)
      {
         if (other.name != null)
            return false;
      }
      else if (!name.equals(other.name))
         return false;
      if (text == null)
      {
         if (other.text != null)
            return false;
      }
      else if (!text.equals(other.text))
         return false;
      if (url == null)
      {
         if (other.url != null)
            return false;
      }
      else if (!url.equals(other.url))
         return false;
      return true;
   }
}
