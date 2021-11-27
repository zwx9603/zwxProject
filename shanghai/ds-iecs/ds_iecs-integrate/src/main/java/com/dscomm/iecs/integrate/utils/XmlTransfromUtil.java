package com.dscomm.iecs.integrate.utils;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.namespace.QName;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class XmlTransfromUtil {

    private static ConcurrentMap<Class, JAXBContext> jaxbContexts = new ConcurrentHashMap<>();

    /**
     * Java Object->Xml with encoding.
     */
    public static String toXml(Object root, Class clazz, String encoding  ) throws Exception {
        try {
            StringWriter writer = new StringWriter();
            createMarshaller(clazz, encoding).marshal(root, writer);
            String xml = writer.toString() ;
            return xml ;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Java Collection->Xml without encoding, ????????Root Element??Collection??????.
     */
    public static String toXml(Collection<?> root, String rootName, Class clazz) {
        return toXml(root, rootName, clazz, null);
    }

    /**
     * Java Collection->Xml with encoding, ????????Root Element??Collection??????.
     */
    public static String toXml(Collection<?> root, String rootName, Class clazz, String encoding) {
        try {
            CollectionWrapper wrapper = new CollectionWrapper();
            wrapper.collection = root;

            JAXBElement<CollectionWrapper> wrapperElement = new JAXBElement<CollectionWrapper>(new QName(rootName),
                    CollectionWrapper.class, wrapper);

            StringWriter writer = new StringWriter();
            createMarshaller(clazz, encoding).marshal(wrapperElement, writer);

            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return  null;
        }
    }

    /**
     * Xml->Java Object.
     */
    public static <T> T fromXml(String xml, Class<T> clazz) {
        try {
            StringReader reader = new StringReader(xml);
            return (T) createUnmarshaller(clazz).unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ????Marshaller??????encoding(????null).
     * ??????????????????????????pooling??
     */
    public static Marshaller createMarshaller(Class clazz, String encoding) {
        try {
            JAXBContext jaxbContext = getJaxbContext(clazz);

            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            if (encoding != null && !encoding.equals("")) {
                marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            }
            return marshaller;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * ????UnMarshaller.
     * ??????????????????????????pooling??
     */
    public static Unmarshaller createUnmarshaller(Class clazz) {
        try {
            JAXBContext jaxbContext = getJaxbContext(clazz);
            return jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }

    }

    protected static JAXBContext getJaxbContext(Class clazz) {
        JAXBContext jaxbContext = jaxbContexts.get(clazz);
        if (jaxbContext == null) {
            try {
                jaxbContext = JAXBContext.newInstance(clazz, CollectionWrapper.class);
                jaxbContexts.putIfAbsent(clazz, jaxbContext);
            } catch (JAXBException ex) {
                throw new RuntimeException("Could not instantiate JAXBContext for class [" + clazz + "]: "
                        + ex.getMessage(), ex);
            }
        }
        return jaxbContext;
    }

    /**
     * ????Root Element ?? Collection??????.
     */
    public static class CollectionWrapper {

        @XmlAnyElement
        protected Collection<?> collection;
    }

}
