/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ product.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un product entity
 */

@Entity
public class ProductEntity extends Model {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Nombre del product
     */
    private String nombre;

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------

    /**
     * Constructor de la clase (sin argumentos)
     */
    public ProductEntity() {
    }

    /**
     * Constructor de la clase (con argumentos)
     * @param nombre
     */
    public ProductEntity(String nombre) {
        this.nombre = nombre;
    }

    //-----------------------------------------------------------
    // Getters & Setters
    //-----------------------------------------------------------

    /**
     * Devuelve el nombre del product
     * @return nombre Nombre del product
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del product
     * @param nombre Nuevo nombre del product
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //-----------------------------------------------------------
    // Métodos auxiliares
    //-----------------------------------------------------------

    /**
     * Crea un objeto product apartir de un nodo Json
     * @param j Nodo Json con atributos y valores de un objeto product
     */
    public static ProductEntity bind(JsonNode j) {
        String nombre = j.findPath("nombre").asText();
        ProductEntity prod = new ProductEntity(nombre);
        return prod;
    }
}
