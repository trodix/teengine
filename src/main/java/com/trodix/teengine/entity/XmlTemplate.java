package com.trodix.teengine.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="templates")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlTemplate {

    @XmlElement
    String template;

    public XmlTemplate() {
        // no op
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    
    
}
