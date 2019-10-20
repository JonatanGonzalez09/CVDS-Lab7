package edu.eci.cvds.view;

import java.sql.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.google.inject.Inject;

import org.primefaces.event.SelectEvent;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

@ManagedBean(name = "AlquilerItemsBean")
@SessionScoped
public class AlquilerItemsBean extends BasePageBean{

    private static final long serialVersionUID = 1L;

    private Cliente clienteSelec;

    @Inject
    private ServiciosAlquiler serviciosAlquiler;

    public Cliente getClienteSelec() throws ExcepcionServiciosAlquiler {
        try{
            return serviciosAlquiler.consultarCliente(clienteSelec.getDocumento());
        }catch(ExcepcionServiciosAlquiler ex){
            throw ex;
        }
    }

    public void registrarCliente(String nombre, long documento, String telefono, String direccion, String email){
        try{
            serviciosAlquiler.registrarCliente(new Cliente(nombre, documento, telefono, direccion, email));
        }catch(ExcepcionServiciosAlquiler excepcionServiciosAlquiler){

        }
    }

    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        List<Cliente> listClientes = null;
        try{
            listClientes = serviciosAlquiler.consultarClientes();
        }catch(ExcepcionServiciosAlquiler ex){
            throw ex;
        }
        return listClientes;
    }

    public void AlquilerItem(int item, int nDias) throws ExcepcionServiciosAlquiler {
        Item objItem = serviciosAlquiler.consultarItem(item);
        try{
            serviciosAlquiler.registrarAlquilerCliente(new Date(System.currentTimeMillis()), clienteSelec.getDocumento(), objItem, nDias);
        }catch(ExcepcionServiciosAlquiler excepcionServiciosAlquiler){

        }
    }
    
    public List<ItemRentado> consulItemsRentados(){
        List<ItemRentado> listRentados = null;
        try {
            listRentados = serviciosAlquiler.consultarItemsCliente(clienteSelec.getDocumento());
        }catch(ExcepcionServiciosAlquiler excepcionServiciosAlquiler){

        }
        return listRentados;
    }

    public long consulCostoAlquiler(int item , int nDias){
        long costo=0;
        try {
            costo= serviciosAlquiler.consultarCostoAlquiler(item, nDias);
        } catch (ExcepcionServiciosAlquiler e) {
            
        }
        return costo;
    }

    public void setClienteSelec(Cliente newClienteSelec) {
        this.clienteSelec = newClienteSelec;
    }

    public void seleccionarCliente(SelectEvent event) throws Exception {
        try{
            long documento= ((Cliente) event.getObject()).getDocumento();
            FacesContext.getCurrentInstance().getExternalContext().redirect("registroalquiler.xhtml?docu="+documento);
        }catch(Exception e){
            throw e;
        }

    }
}