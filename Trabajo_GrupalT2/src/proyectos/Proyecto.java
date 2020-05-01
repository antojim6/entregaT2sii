/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import usuarios.ONG;
import usuarios.Docencia;

@Entity
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "id", nullable = false, length = 50)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;
    @Column(name = "localidad", nullable = false, length = 50)
    private String localidad;
    @ManyToOne
    private ONG ong; //Varios proyectos son organizados por una ONG
    @ManyToMany (mappedBy = "CoordinaProyecto")
    private List<Docencia> coordinatedByDocencia;
    @OneToMany (mappedBy="proyecto")
    private List<Actividad> actividades;
    
    public Proyecto(){}
    public Proyecto(Long id,String nombre, String desc, String localidad){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = desc;
        this.localidad =localidad;
        this.setActividades(new ArrayList<>());
    }
    
    // Métodos Getters y Setters
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public ONG getONG(){
        return this.ong;
    } 
    public String getLocalidad() {
        return localidad;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    public List<Actividad> getActividades(){
        return this.actividades;
    }
    public void setActividades(List<Actividad> actividades){
        this.actividades = actividades;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    // Métodos relacionados con el manejo de la listas de actividades dentro del proyecto
    public void addActividad(Actividad act){
        this.actividades.add(act);
    }
    public void borrar_actividad (Actividad act){
        this.actividades.remove(act);
    }
    public int getIndex (Actividad act){
        return this.actividades.indexOf(act);
    }
    public void borrar_actividad (int index){
        this.actividades.remove(this.actividades.get(index));
    }

    // Métodos relacionados con equals
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    // Método toString
    @Override
    public String toString() {
        return "proyectos.Proyecto[ id=" + id + " ]";
    }
    
}
