package edu.eci.cvds.sampleprj.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.cvds.samples.entities.Cliente;

public interface ClienteDAO {

   public Cliente loadCliente(long id) throws PersistenceException;
   
   public List<Cliente> loadClientes() throws PersistenceException;

   public void updateCliente(long id, Boolean vet)throws PersistenceException;

   public void registrarAlquilerCliente(Date fechainiciorenta , long docu, int item, Date fechafinrenta) throws PersistenceException;

   public void insertCliente(Cliente c)throws PersistenceException;
}
