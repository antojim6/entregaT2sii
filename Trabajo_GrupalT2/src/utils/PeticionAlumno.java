/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import proyectos.Actividad;
import usuarios.Alumno;


/**
 *
 * @author pablojoel
 */
public class PeticionAlumno {
    
    private Alumno usr;
    private Actividad act;
    
    public PeticionAlumno(){
        
    }
    
    public PeticionAlumno(Alumno usr, Actividad act){
        this.usr = usr;
        this.act = act;
    }
    
    public Alumno getAlumno(){
        return this.usr;
    }
    
    public void setDocencia(Alumno usr){
        this.usr = usr;
    }
    
    public Actividad getActividad(){
        return this.act;
    }
    
    public void setActividad(Actividad act){
        this.act = act;
    }
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (usr.getId() != null ? usr.getId().hashCode() : 0);
        hash += (act.getId() != null ? act.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeticionAlumno)) {
            return false;
        }
        PeticionAlumno other = (PeticionAlumno) object;
        if ((this.usr.getId() == null && other.usr.getId() != null) || (this.usr.getId() != null && !this.usr.getId().equals(other.usr.getId()))) {
            return false;
        }
        
        if ((this.act.getId() == null && other.act.getId() != null) || (this.act.getId() != null && !this.act.getId().equals(other.act.getId()))) {
            return false;
        }
        return true;
    }
    
}

