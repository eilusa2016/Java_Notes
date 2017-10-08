
package org.xjb.cxf.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.xjb.cxf.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllCats_QNAME = new QName("http://ws.cxf.xjb.org/", "getAllCats");
    private final static QName _GetAllCatsResponse_QNAME = new QName("http://ws.cxf.xjb.org/", "getAllCatsResponse");
    private final static QName _GetCatByUser_QNAME = new QName("http://ws.cxf.xjb.org/", "getCatByUser");
    private final static QName _GetCatByUserResponse_QNAME = new QName("http://ws.cxf.xjb.org/", "getCatByUserResponse");
    private final static QName _SayHi_QNAME = new QName("http://ws.cxf.xjb.org/", "sayHi");
    private final static QName _SayHiResponse_QNAME = new QName("http://ws.cxf.xjb.org/", "sayHiResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.xjb.cxf.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllCats }
     * 
     */
    public GetAllCats createGetAllCats() {
        return new GetAllCats();
    }

    /**
     * Create an instance of {@link GetAllCatsResponse }
     * 
     */
    public GetAllCatsResponse createGetAllCatsResponse() {
        return new GetAllCatsResponse();
    }

    /**
     * Create an instance of {@link GetCatByUser }
     * 
     */
    public GetCatByUser createGetCatByUser() {
        return new GetCatByUser();
    }

    /**
     * Create an instance of {@link GetCatByUserResponse }
     * 
     */
    public GetCatByUserResponse createGetCatByUserResponse() {
        return new GetCatByUserResponse();
    }

    /**
     * Create an instance of {@link SayHi }
     * 
     */
    public SayHi createSayHi() {
        return new SayHi();
    }

    /**
     * Create an instance of {@link SayHiResponse }
     * 
     */
    public SayHiResponse createSayHiResponse() {
        return new SayHiResponse();
    }

    /**
     * Create an instance of {@link StringCat }
     * 
     */
    public StringCat createStringCat() {
        return new StringCat();
    }

    /**
     * Create an instance of {@link Entry }
     * 
     */
    public Entry createEntry() {
        return new Entry();
    }

    /**
     * Create an instance of {@link Cat }
     * 
     */
    public Cat createCat() {
        return new Cat();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cxf.xjb.org/", name = "getAllCats")
    public JAXBElement<GetAllCats> createGetAllCats(GetAllCats value) {
        return new JAXBElement<GetAllCats>(_GetAllCats_QNAME, GetAllCats.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCatsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cxf.xjb.org/", name = "getAllCatsResponse")
    public JAXBElement<GetAllCatsResponse> createGetAllCatsResponse(GetAllCatsResponse value) {
        return new JAXBElement<GetAllCatsResponse>(_GetAllCatsResponse_QNAME, GetAllCatsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCatByUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cxf.xjb.org/", name = "getCatByUser")
    public JAXBElement<GetCatByUser> createGetCatByUser(GetCatByUser value) {
        return new JAXBElement<GetCatByUser>(_GetCatByUser_QNAME, GetCatByUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCatByUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cxf.xjb.org/", name = "getCatByUserResponse")
    public JAXBElement<GetCatByUserResponse> createGetCatByUserResponse(GetCatByUserResponse value) {
        return new JAXBElement<GetCatByUserResponse>(_GetCatByUserResponse_QNAME, GetCatByUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHi }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cxf.xjb.org/", name = "sayHi")
    public JAXBElement<SayHi> createSayHi(SayHi value) {
        return new JAXBElement<SayHi>(_SayHi_QNAME, SayHi.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cxf.xjb.org/", name = "sayHiResponse")
    public JAXBElement<SayHiResponse> createSayHiResponse(SayHiResponse value) {
        return new JAXBElement<SayHiResponse>(_SayHiResponse_QNAME, SayHiResponse.class, null, value);
    }

}
