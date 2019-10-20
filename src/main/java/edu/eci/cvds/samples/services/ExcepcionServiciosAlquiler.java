package edu.eci.cvds.samples.services;

public class ExcepcionServiciosAlquiler extends Exception{

    private static final long serialVersionUID = 1L;
    
    public ExcepcionServiciosAlquiler(String mensaje, Throwable causa){
        super(mensaje,causa);
    } 

    public ExcepcionServiciosAlquiler(String mensaje){
        super(mensaje);
    } 
}