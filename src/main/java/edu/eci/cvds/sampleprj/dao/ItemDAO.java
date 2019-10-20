package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.cvds.samples.entities.Item;

public interface ItemDAO {

   public List<Item> loadItems() throws PersistenceException;

   public Item loadItem(int id) throws PersistenceException;
   
   public void insertItem(Item it) throws PersistenceException;

   public List<Item> loadItemsDisponibles() throws PersistenceException;

   public void updateTarifaitem(int id, long tarifa) throws PersistenceException;
   

}