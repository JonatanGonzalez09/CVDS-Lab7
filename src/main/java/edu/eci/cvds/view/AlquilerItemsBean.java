package edu.eci.cvds.view;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

@ManagedBean(name = "AlquilerItemsBean")
@SessionScoped
public class AlquilerItemsBean extends BasePageBean {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private long documento;
    private String telefono;
    private String direccion;
    private String email;
    private boolean vetado;
    private int idItem;
    private int numDias;
    private long costoAlq;

    @Inject
    private ServiciosAlquiler serviciosAlquiler;

    /** Vista cliente */
    public void registrarCliente()
            throws ExcepcionServiciosAlquiler {
                try {
                    System.out.println("registrar");
                    System.out.println(this.documento);
                    System.out.println(this.nombre);
                    Cliente cli = new Cliente(this.nombre, this.documento, this.telefono, this.direccion, this.email, false,
                            null);
                    serviciosAlquiler.registrarCliente(cli);
                } catch (ExcepcionServiciosAlquiler ex) {
                    throw ex;
                }
    }

    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        List<Cliente> listClientes = null;
        try {
            listClientes = serviciosAlquiler.consultarClientes();
        } catch (ExcepcionServiciosAlquiler ex) {
            throw ex;
        }
        return listClientes;
    }

    public void seleccionarCliente() throws Exception {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("registroalquiler.xhtml?documento=" + documento);
            System.out.println ("documento: "+documento);
        } catch (Exception e) {
            throw e;
        }

    }

    /** vista Alquiler */
    public List<ItemRentado> consulItemsRentados() {
        List<ItemRentado> listRentados = null;
        try {
            listRentados = serviciosAlquiler.consultarItemsCliente(documento);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {

        }
        return listRentados;
    }

    public void registarAlquiler() throws ExcepcionServiciosAlquiler {
        Item objItem = serviciosAlquiler.consultarItem(idItem);
        try {
            serviciosAlquiler.registrarAlquilerCliente(new Date(System.currentTimeMillis()), documento, objItem, numDias);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {

        }
    }

    public long multaAlquiler(int iditem) throws ExcepcionServiciosAlquiler, ParseException {
        try{
            Calendar fecha = Calendar.getInstance();
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            int mes = fecha.get(Calendar.MONTH);
            int año = fecha.get(Calendar.YEAR);
            String fechaSis = año + "/" + (mes) + "/" + dia ;
            System.out.println(fechaSis);
            Date fechaSistema = (Date) new SimpleDateFormat("yyyy/MM/dd").parse(fechaSis);
            return serviciosAlquiler.consultarMultaAlquiler(iditem, fechaSistema);
        }catch(ExcepcionServiciosAlquiler ex){
            throw ex;

        }
    }

    public void consultarCosto() throws ExcepcionServiciosAlquiler {

        try {
            System.out.println("[item: " + idItem);
            System.out.println("[dias: " + numDias);
            long costo = serviciosAlquiler.consultarCostoAlquiler(this.idItem, this.numDias);
            System.out.println("[costo: " + costo);
            //setCostoAlq(costo);

        } catch (ExcepcionServiciosAlquiler ex) {
            throw ex;
        }

    }

    public void reiniciar() throws IOException {
        this.nombre=null;
        this.documento=0;this.telefono=null;this.email=null;this.numDias=0;this.idItem=0;
        FacesContext.getCurrentInstance().getExternalContext().redirect("registrocliente.xhtml");

    }
    
   
    public void setDocumento(long documento){
        this.documento = documento;
    }

    public long getDocumento(){
        return this.documento;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public String getDireccion(){
        return this.direccion;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public String getTelefono(){
        return this.telefono;
    }
    
    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }
    
    public void setIdItem(int idItem){
        this.idItem = idItem;
    }

    public int getIdItem(){
        return this.idItem;
    }

    public void setnumDias(int numDias){
        this.numDias = numDias;
    }

    public int getnumDias(){
        return this.numDias;
    }

    public void setCostoAlq(long costoAlq){
        this.costoAlq = costoAlq;
    }

    public long getCostoAlq(){
        return this.costoAlq;
    }
}