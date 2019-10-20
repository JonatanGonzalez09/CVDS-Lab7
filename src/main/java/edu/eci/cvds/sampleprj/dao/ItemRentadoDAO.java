package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.cvds.samples.entities.ItemRentado;

public interface ItemRentadoDAO {

   public ItemRentado consultarItemRentado(int id) throws PersistenceException;

   public List<ItemRentado> loadItemsCliente(long id) throws PersistenceException;


}