//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.01.23 at 10:56:56 AM BRST 
//


package br.com.nascisoft.apoioterritoriols.admin.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegiaoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegiaoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificador" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="identificadorCidade" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="letra" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nivelZoom" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="latitudeCentro" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="longitudeCentro" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegiaoType", propOrder = {
    "identificador",
    "identificadorCidade",
    "nome",
    "letra",
    "nivelZoom",
    "latitudeCentro",
    "longitudeCentro"
})
public class RegiaoType {

    protected long identificador;
    protected long identificadorCidade;
    @XmlElement(required = true)
    protected String nome;
    @XmlElement(required = true)
    protected String letra;
    protected int nivelZoom;
    protected double latitudeCentro;
    protected double longitudeCentro;

    /**
     * Gets the value of the identificador property.
     * 
     */
    public long getIdentificador() {
        return identificador;
    }

    /**
     * Sets the value of the identificador property.
     * 
     */
    public void setIdentificador(long value) {
        this.identificador = value;
    }

    /**
     * Gets the value of the identificadorCidade property.
     * 
     */
    public long getIdentificadorCidade() {
        return identificadorCidade;
    }

    /**
     * Sets the value of the identificadorCidade property.
     * 
     */
    public void setIdentificadorCidade(long value) {
        this.identificadorCidade = value;
    }

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the letra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLetra() {
        return letra;
    }

    /**
     * Sets the value of the letra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLetra(String value) {
        this.letra = value;
    }

    /**
     * Gets the value of the nivelZoom property.
     * 
     */
    public int getNivelZoom() {
        return nivelZoom;
    }

    /**
     * Sets the value of the nivelZoom property.
     * 
     */
    public void setNivelZoom(int value) {
        this.nivelZoom = value;
    }

    /**
     * Gets the value of the latitudeCentro property.
     * 
     */
    public double getLatitudeCentro() {
        return latitudeCentro;
    }

    /**
     * Sets the value of the latitudeCentro property.
     * 
     */
    public void setLatitudeCentro(double value) {
        this.latitudeCentro = value;
    }

    /**
     * Gets the value of the longitudeCentro property.
     * 
     */
    public double getLongitudeCentro() {
        return longitudeCentro;
    }

    /**
     * Sets the value of the longitudeCentro property.
     * 
     */
    public void setLongitudeCentro(double value) {
        this.longitudeCentro = value;
    }

}
