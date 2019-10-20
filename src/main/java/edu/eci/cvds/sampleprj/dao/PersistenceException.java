package edu.eci.cvds.sampleprj.dao;

public class PersistenceException extends Exception{

    private static final long serialVersionUID = 1L;
    
    public PersistenceException(String mensaje, Throwable causa){
        super(mensaje,causa);
    } 

    public PersistenceException(String mensaje){
        super(mensaje);
    } 
}