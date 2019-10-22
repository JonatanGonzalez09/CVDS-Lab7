package edu.eci.cvds.view;

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
    private ArrayList<ItemRentado> rentados;

    @Inject
    private ServiciosAlquiler serviciosAlquiler;

    /** Vista cliente */
    public void registrarCliente(String nombre, long documento, String telefono, String direccion, String email)
            throws ExcepcionServiciosAlquiler {
        try {
            serviciosAlquiler.registrarCliente(new Cliente(nombre, documento, telefono, direccion, email));
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

    public void registarAlquiler(int item, int nDias) throws ExcepcionServiciosAlquiler {
        Item objItem = serviciosAlquiler.consultarItem(item);
        try {
            serviciosAlquiler.registrarAlquilerCliente(new Date(System.currentTimeMillis()), documento, objItem, nDias);
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

    public void setDoc(long newDocumento){
        this.documento = newDocumento;
    }

    public long getDoc(){
        return this.documento;
    }
}