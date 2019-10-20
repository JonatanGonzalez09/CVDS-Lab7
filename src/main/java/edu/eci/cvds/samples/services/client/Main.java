package edu.eci.cvds.samples.services.client;

import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

public class Main {

    public static void main(String[] args) throws ExcepcionServiciosAlquiler {
        //Integer num=11;
        //long x = num.longValue();
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarCliente(1026585664));
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarClientes());
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarItemsCliente(1026585664));
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarItemsDisponibles());
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarTiposItem());
        System.exit(0);
    }
}