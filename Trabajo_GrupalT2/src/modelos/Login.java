/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import usuarios.Alumno;
import usuarios.ONG;
import usuarios.Docencia;

@Named(value = "login")
@RequestScoped
public class Login {

    private String correo;
    private String contrasenia;
    private List<Alumno> alumnos;
    private List<Docencia> Docencia;
    private List<ONG> ONG;
    
    @Inject
    private ControlAutorizacion ctrl;
    @Inject
    private ControlProyectos ctrlProyectos;

    /**
     * Creates a new instance of Login
     */
    public Login() {
        alumnos = new ArrayList<>();
        ONG = new ArrayList<>();
        Docencia = new ArrayList<>();
       

        // Usuarios
        alumnos.add(new Alumno("Manolo", "de la Torre", "666666666", "C/Alguna" , "user@user.com", "user", "Informática"));
        Docencia.add(new Docencia("Pepin", "de la Torre", "666666666", "C/Puede" , "doc@doc.com", "doc","Salud","Biología"));
        ONG.add(new ONG("Federico", "de la Torre", "666666666", "C/Ninguna" , "ong@ong.com", "ong", "Caritas", "Descripción"));
    }
    
    // Método de autentificación del inicio de sesión
    public String autenticar() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        String regex = "^[A-Za-z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        
        if(matcher.matches()){
            for (Alumno next : alumnos) {
                if(next.getCorreo().equals(correo)){
                    Alumno usr = next;
                    if(comprobarContrasenia(usr)){
                        ctrl.setUsuario(usr);
                        ctrl.setRol(0);
                        ctrlProyectos.setAlumno(usr);
                        return ctrl.homeAlumno();
                    } else {
                        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Coontrasenia no valida!", "Coontrasenia no valida!")); 
                    }
                }
            }
            for (ONG next : ONG) {
                if(next.getCorreo().equals(correo)){
                    ONG usr = next;
                    if(comprobarContrasenia(usr)){
                        ctrl.setUsuario(usr);
                        ctrl.setRol(1);
                        ctrlProyectos.setONG(usr);
                        return ctrl.homeONG();
                    } else {
                        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Coontrasenia no valida!", "Coontrasenia no valida!")); 
                    }
                }
            }
            for (Docencia next : Docencia) {
                if(next.getCorreo().equals(correo)){
                    Docencia usr = next;
                    if(comprobarContrasenia(usr)){
                        ctrl.setUsuario(usr);
                        ctrl.setRol(2);
                        ctrlProyectos.setDocencia(usr);
                        return ctrl.homeDocencia();
                    } else {
                        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Coontrasenia no valida!", "Coontrasenia no valida!")); 
                    }
                }
            }
        }
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correo no valido!", "Correo no valido!"));
        return null;
    }
    
    // Comprobamos que el código hash de la contraseña coincide
    private boolean comprobarContrasenia(Usuario usr){
        return usr.getHashPassword().equals(contrasenia);
    }
    
    // Getters y setters útiles
    public String getCorreo() {
        return correo;
    }
    public String getContrasenia() {
        return contrasenia;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

}
