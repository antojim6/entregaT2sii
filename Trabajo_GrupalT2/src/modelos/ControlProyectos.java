/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;
import java.io.Serializable;
import java.text.ParseException;
import proyectos.Actividad;    
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import proyectos.Proyecto;
import usuarios.ONG;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import usuarios.Alumno;
import usuarios.Docencia;
import utils.PeticionAdmin;
import utils.PeticionAlumno;

@Named(value = "ControlProyectos")
@ApplicationScoped
public class ControlProyectos implements Serializable {
     // Variables del objeto
     private List<Actividad> listaActividades;
     private List<Proyecto> listaProyectos;
     //private Usuario usuario;
     private Alumno alumno;
     private ONG ong;
     private Docencia docencia;
     private List<PeticionAdmin> peticiones;
     private List<PeticionAlumno> peticionesAlumno;
     
    // Crea un nuevo objeto ControlProyectos y hacemos una asignación inicial de proyectos y actividades para la prueba
    public ControlProyectos() throws ParseException {
       listaActividades = new ArrayList<Actividad>();
       listaProyectos = new ArrayList<Proyecto>();
       peticionesAlumno = new ArrayList<>();
       peticiones = new ArrayList();
       
       Proyecto p1 = new Proyecto(1L, "A COMER", "Recogida de alimentos para las personas mayores o necesitadas", "Málaga");
       Proyecto p2 = new Proyecto(2L, "A LIMPIAR!", "Recogida de basura para la protección de flora y fauna", "La línea");
       Proyecto p3 = new Proyecto(3L, "Protectora de animales", "Cuidado de animales callejero y/o abandonados", "Barcelona");
       
       Actividad a1 = new Actividad(1L,"Recogida de alimentos", "Se buscan voluntarios para recoger alimentos","Cualquier ámbito", new SimpleDateFormat("dd/MM/yyyy").parse("17/12/2019"),new SimpleDateFormat("dd/MM/yyyy").parse("17/12/2020"),p1);
       Actividad a2 = new Actividad(2L,"Comedor social", "Se busca voluntariado para que realicen distintas tareas dentro del comedor social", "Cocina", new SimpleDateFormat("dd/MM/yyyy").parse("11/12/2019"), new SimpleDateFormat("dd/MM/yyyy").parse("20/4/2020"),p1);
       Actividad a3 = new Actividad(3L,"Recogida de basuras del agua", "Uso de redes y barcos para la recogida de plásticos y otros materiales contaminantes","Náutica",new SimpleDateFormat("dd/MM/yyyy").parse("18/08/2020"),new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2020"),p2);
       Actividad a4 = new Actividad(4L,"Limpieza de la arena", "Se buscan voluntarios para limpiar la arena","Cualquier ámbito",new SimpleDateFormat("dd/MM/yyyy").parse("17/12/2019"),new SimpleDateFormat("dd/MM/yyyy").parse("17/12/2020"),p2);
       Actividad a5 = new Actividad(5L,"Creación de la página web", "Se requiere de un programador de páginas web para que gestione las adopciones por internet","Informatica",new SimpleDateFormat("dd/MM/yyyy").parse("01/05/2019"),new SimpleDateFormat("dd/MM/yyyy").parse("30/01/2020"),p3);
       Actividad a6 = new Actividad(6L,"Rehabilitación y revisión de los animales", "Voluntarios veterinarios para que realicen los distintos cuidados necesarios para los animales","Veterinaria",new SimpleDateFormat("dd/MM/yyyy").parse("05/03/2020"),new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2021"),p3);
       
       List<Actividad> listaActividades1 = new ArrayList<>();
       List<Actividad> listaActividades2 = new ArrayList<>();
       List<Actividad> listaActividades3 = new ArrayList<>();
       
       listaActividades1.add(a1); listaActividades1.add(a2);
       p1.setActividades(listaActividades1);
       
       listaActividades2.add(a3); listaActividades2.add(a4);
       p2.setActividades(listaActividades2);
       
       listaActividades3.add(a5); listaActividades3.add(a6);
       p3.setActividades(listaActividades3);
       
       listaActividades.add(a1);
       listaActividades.add(a2);
       listaActividades.add(a3);
       listaActividades.add(a4);
       listaActividades.add(a5);
       listaActividades.add(a6);
       
       listaProyectos.add(p1);
       listaProyectos.add(p2);
       listaProyectos.add(p3);

    }
    
    // Añadimos una nueva petición a la lista de peticiones
    public void aniadirProyectoPeticion(Proyecto proy){
        this.peticiones.add(new PeticionAdmin(docencia, proy));
    }
    
    // Devuelve todos los proyectos de la base de datos en las que el personal docente no está participando
    public List<Proyecto> getTodosProyectos() {
            List<Proyecto> aux = new ArrayList<Proyecto>();
            //List<Proyecto> listUsr = docencia.getProyectos();
            for(Proyecto next:listaProyectos){
                boolean found = false;
                for (PeticionAdmin next1: peticiones){
                    if(next1.equals(new PeticionAdmin(docencia,next))){
                        found = true;
                    }
                }
                if(!docencia.getProyectos().contains(next) && !found){
                    aux.add(next);
                }
            }
        return aux;
    }
    // Devuelve todas las actividades de la base de datos en las que el alumno no está enrolado
    public List<Actividad> getTodasActividades() {
            List<Actividad> aux = new ArrayList<Actividad>();
            List<Actividad> listUsr = alumno.getActividades();
            
            if(listUsr.isEmpty()){
                return listaActividades;
            }else{
                
            for(Actividad next:listaActividades){
                if(!listUsr.contains(next)){
                    aux.add(next);
                }
            }
        }
        return aux;
    }

    // Devuelve toda la lista de peticiones de los proyectos actuales
    public List<PeticionAdmin> getMisPeticiones(){
        List<PeticionAdmin> aux = new ArrayList();
        for(PeticionAdmin next:peticiones){
                        if(ong.getProyectos().contains(next.getProyecto())){
                aux.add(next);
            }
        }
        return aux;
    }
    
    // Devuelve la lista de los proyectos almacenados en la clase docencia
    public List<Proyecto> getMisProyectosDocencia(){
        return docencia.getProyectos();
    }
    
    // Devuelve la lista de peticiones hechas por los alumnos a las distintas actividades
    public List<PeticionAlumno> getMisPeticionesAlumno(){
        List<PeticionAlumno> aux = new ArrayList<>();
        for(PeticionAlumno next: peticionesAlumno){
            if(docencia.busquedaActividad(next.getActividad())){
                aux.add(next);
            }
        }
        return aux;
    }  
    
    
    //Métodos para aceptar, rechazar y enviar peticiones 
    public void aceptarPeticion(Docencia doc, Proyecto proy){
        ong.getListaDocencia().add(doc);
        doc.getProyectos().add(proy);
        peticiones.remove(new PeticionAdmin(doc,proy));
    }
    public void rechazarPeticion(Docencia doc, Proyecto proy){
         peticiones.remove(new PeticionAdmin(doc,proy));
    }
    public void aceptarPeticionAlumno(PeticionAlumno p){
        List<Actividad> lista = p.getAlumno().getActividades();
        lista.add(p.getActividad());
        p.getAlumno().setActividades(lista);
        peticionesAlumno.remove(p);
    }
    public void rechazarPeticionAlumno(PeticionAlumno p){
        peticionesAlumno.remove(p);
    }
    public void mandarPeticion(Actividad a){
        PeticionAlumno pa = new PeticionAlumno(alumno, a);
        if(!peticionesAlumno.contains(pa)) peticionesAlumno.add(pa);
    }
    
    // Métodos CRUD relacionados con los proyectos
    // Método que usamos para crear un proyecto y añadirlo a la base de datos
    public void crearProyecto(String nombre, String loc, String desc){
        if(nombre.equals(""))
            return;
        long id;
        if(listaProyectos.isEmpty())
            id = 0L;
        else
            id = listaProyectos.get(listaProyectos.size()-1).getId() + 1;
        Proyecto p = new Proyecto(id,nombre, loc, desc);
        listaProyectos.add(p);
        List<Proyecto> pl = ong.getProyectos();
        pl.add(p);
        ong.setProyectos(pl);
    }
    // Método para editar un proyecto en concreto y cambiar sus parámetros
    public void editarProyecto(String PSeleccionadoString, String loc, String desc){
        Proyecto PSeleccionado = null;
        for (Proyecto p : ong.getProyectos()) {
            if(p.toString().equals(PSeleccionadoString)){
                PSeleccionado = p;
                break;
            }
        }
        if(!loc.equals(""))
            PSeleccionado.setLocalidad(loc);
        if(!desc.equals(""))
            PSeleccionado.setDescripcion(desc);
    }
    // Método para borrar un proyecto
    public void borrarProyecto(Proyecto p){
        listaProyectos.remove(p);
        ong.getProyectos().remove(p);
        if(docencia != null)
            docencia.getProyectos().remove(p);
        if(!p.getActividades().isEmpty()){
            for(Actividad act : p.getActividades()){
                listaActividades.remove(act);
                if(alumno!=null){
                    alumno.getActividades().remove(act);
                } 
            }
        }
    }
    
    
    // Métodos CRUD relacionados con las actividades
    public void crearActividad(String nombre, String descripcion, String conocimientos, String fecha_inicio, String fecha_final, String p){
        if(p=="") return;
        long id;
        if(listaActividades.isEmpty())
            id = 0L;
        else
            id = listaActividades.get(listaActividades.size()-1).getId() + 1;
        Actividad act;
        try {
             Proyecto pfinal=null;
             for(Proyecto paux:listaProyectos){
                 if(paux.toString().equals(p))pfinal=paux;
             }
             act = new Actividad(id,nombre,descripcion,conocimientos,new SimpleDateFormat("dd/MM/yyyy").parse(fecha_inicio),new SimpleDateFormat("dd/MM/yyyy").parse(fecha_final),pfinal);
             listaActividades.add(act);
             pfinal.addActividad(act);
         } catch (ParseException ex) {Logger.getLogger(ControlProyectos.class.getName()).log(Level.SEVERE, null, ex);
         } catch(Exception e){} 
    }
    public String actividadFechaInicioToString(Actividad a){
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(a.getFecha_inicio());
        return date;
    }
    public String actividadFechaFinalToString(Actividad a){
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(a.getFecha_finalizacion());
        return date;
    }
    public void editarActividad(String nombre, String descripcion, String conocimientos, String fecha_inicio, String fecha_final){
        Actividad ActSelect = null;
        for (Actividad a : listaActividades) {
            if(a.toString().equals(nombre)){
                ActSelect = a;
                break;
            }
        }
        try{
            if(!descripcion.equals(""))
                ActSelect.setDescripcion(descripcion);
            if(!conocimientos.equals(""))
                ActSelect.setConocimientos_necesarios(conocimientos);
            if(!fecha_inicio.equals(""))
                ActSelect.setFecha_inicio(new SimpleDateFormat("dd/MM/yyyy").parse(fecha_inicio));
            if(!fecha_final.equals(""))
                ActSelect.setFecha_finalizacion(new SimpleDateFormat("dd/MM/yyyy").parse(fecha_final));
        } catch (ParseException c){}
    }
    // Elimina una actividad en concreto mediante se representación en un String 
    public void eliminarActividad(String a){
        Actividad temp = null;
        for (Actividad ite: listaActividades){
            if (a.equals(ite.toString())){
                temp = ite;
                break;
            }
        }
        // Borramos la actividad de todas las listas
        listaActividades.remove(temp);
        if (alumno != null){
            alumno.getActividades().remove(temp);
        }
        temp.getProyecto().getActividades().remove(temp);
    }
    // Elimina una actividad en concreto pasándole como parámetro el objeto que debe ser borrado
    public void eliminar(Actividad a){
        alumno.getActividades().remove(a);
    }
    
    // Getters y setters útiles
    public List<Actividad> getListaActividades(){
        return listaActividades;
    }
    public List<Proyecto> getMisProyectos(){
        return ong.getProyectos();
    }
    
    public List<Actividad> getMisActividadesDocencia(){
        return docencia.getActividadesDocencia();
    }
    public List<Actividad> getMisActividades() {
        return alumno.getActividades();
    }
    public void setAlumno(Alumno usr){
        this.alumno = usr;
    }
    public void setONG(ONG usr) {
       this.ong = usr;
       for(Proyecto p : listaProyectos){
           ong.getProyectos().add(p);
       }
    }
    public void setDocencia(Docencia usr){
       this.docencia = usr;
    }
    
}
