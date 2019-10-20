package edu.eci.cvds.test;

import java.sql.Date;

import com.google.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

public class ServiciosAlquilerTest {
	
    @Inject
    ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    @Before
    public void setUp() {
    }

    @Test
    public void deberiaRegistrarNuevosClientes(){
        try{
            Cliente cliente = new Cliente("Andrea", 1233897028 ,"9056754", "Calle 113c #143A-20 ", "andrea@gmail.com");
            serviciosAlquiler.registrarCliente(cliente);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deberiaConsultarClientes(){
        try{
            Cliente cliente = new Cliente("Andrea", 1233897028 ,"9056754", "Calle 113c #143A-20 ", "andrea@gmail.com");
            serviciosAlquiler.registrarCliente(cliente);
            Cliente consulta = serviciosAlquiler.consultarCliente(cliente.getDocumento());
            Assert.assertTrue(cliente.getDocumento() == consulta.getDocumento());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deberiaRegistrarAlquilerCliente(){
        try{
            Cliente cliente = new Cliente("Andrea", 1233897028 ,"9056754", "Calle 113c #143A-20 ", "andrea@gmail.com");
            TipoItem ti = new TipoItem(1,"Videojuego");
            Date fecha =  new Date(2019,12,12);
            Item it = new Item(ti, 1, "Item1", "Primer Item", fecha, 20000, "", "Masculino");
            serviciosAlquiler.registrarAlquilerCliente(fecha, cliente.getDocumento(), it, 8);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void deberiaObtenerItemsNoRegresados() {
    	try {
    		serviciosAlquiler.consultarItemsCliente(4);
    	}catch(Exception e) {
    		e.getMessage();
    	}
    }
    
    @Test
    public void deberiaCalcularLaMulta() {
    	try {
    		TipoItem ti = new TipoItem(1,"Videojuego");
    		Date fecha =  new Date(2019,10,11);
    		Item it = new Item(ti, 1, "Item1", "Primer Item", fecha, 20000, "", "Masculino");
    		System.out.println(serviciosAlquiler.consultarMultaAlquiler(1, new Date(2019,10,11)));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}