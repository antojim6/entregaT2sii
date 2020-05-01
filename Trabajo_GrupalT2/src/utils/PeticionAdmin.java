/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import proyectos.Proyecto;
import usuarios.Docencia;


/**
 *
 * @author pablojoel
 */
public class PeticionAdmin {
    
    private Docencia docente;
    private Proyecto proyectos;
    
    public PeticionAdmin(){
        
    }
    
    public PeticionAdmin(Docencia doc, Proyecto proy){
        this.docente = doc;
        this.proyectos = proy;
    }
    
    public Docencia getDocencia(){
        return this.docente;
    }
    
    public void setDocencia(Docencia doc){
        this.docente = doc;
    }
    
    public Proyecto getProyecto(){
        return this.proyectos;
    }
    
    public void setProyecto(Proyecto proy){
        this.proyectos = proy;
    }
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (docente.getId() != null ? docente.getId().hashCode() : 0);
        hash += (proyectos.getId() != null ? proyectos.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeticionAdmin)) {
            return false;
        }
        PeticionAdmin other = (PeticionAdmin) object;
        if ((this.docente.getId() == null && other.docente.getId() != null) || (this.docente.getId() != null && !this.docente.getId().equals(other.docente.getId()))) {
            return false;
        }
        
        if ((this.proyectos.getId() == null && other.proyectos.getId() != null) || (this.proyectos.getId() != null && !this.proyectos.getId().equals(other.proyectos.getId()))) {
            return false;
        }
        return true;
    }
    
}
