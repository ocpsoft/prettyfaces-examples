package com.ocpsoft.pretty.blog.beans;

import java.util.Iterator;
import java.util.Map;

import javax.enterprise.event.Observes;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.PreRenderViewEvent;
import javax.inject.Inject;

public class SetFocusListener
{
   private static final String STYLE_CLASS = "styleClass";
   private static final String FAILED_VALIDATION_STYLE = "errorHighlight";

   @Inject
   FacesContext facesContext;

   public void beforePhase(@Observes final PreRenderViewEvent event)
   {
      String focus = "";

      Iterator<String> clientIdsWithMessages = facesContext.getClientIdsWithMessages();
      while (clientIdsWithMessages.hasNext())
      {
         String clientId = clientIdsWithMessages.next();
         if (clientId != null)
         {
            if ("".equals(focus))
            {
               focus = clientId;
            }

            UIComponent component = event.getComponent().findComponent(clientId);
            if (component != null)
            {
               Map<String, Object> attributes = component.getAttributes();
               String styleClass = (String) attributes.get(STYLE_CLASS);
               if ((styleClass == null) || "".equals(styleClass))
               {
                  attributes.put(STYLE_CLASS, FAILED_VALIDATION_STYLE);
               }
               else if (!styleClass.contains(FAILED_VALIDATION_STYLE))
               {
                  attributes.put(STYLE_CLASS, styleClass + " " + FAILED_VALIDATION_STYLE);
               }
            }
         }
      }
      facesContext.getExternalContext().getRequestMap().put("focus", focus.replaceAll(":", "\\\\\\\\:"));
   }
}