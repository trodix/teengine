package com.trodix.teengine.service;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.trodix.teengine.entity.XmlTemplate;

import org.springframework.stereotype.Service;

@Service
public class XmlFileParser {

    public XmlTemplate parse(InputStream inputStream) {

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(XmlTemplate.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            XmlTemplate templateFile = (XmlTemplate) jaxbUnmarshaller.unmarshal(inputStream);

            return templateFile;

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;

    }

}
