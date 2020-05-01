/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import usuarios.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;

@Named(value = "controlAutorizacion")
@SessionScoped
public class ControlAutorizacion implements Serializable {
    // Variables del objeto
    private Usuario usuario;
    private int rol;                    //0: Alumno, 1: ONG, 2: Docencia
    
    /**
     * Creates a new instance of ControlAutorizacion
    */
    public ControlAutorizacion() {
    }
    
    // Métodos útiles getters y setters
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getRol(){
        return rol;
    }

    public void setRol(int rol){
        this.rol = rol;
    }
    // Métodos utiles que nos devuelven la cadena con el nombre de su página html
    public String homeAlumno() {
        return "usuarioAlumno.xhtml";
    }

    public String homeONG() {
        return "usuarioOng.xhtml";
    }

    public String homeDocencia() {
        return "usuarioDocencia.xhtml";
    }
    
    
    // Destruye la sesión actual
    public String logout()
    {
        // Destruye la sesión (y con ello, el ámbito de este bean)
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        usuario = null;
        return "login.xhtml";
    }
}
