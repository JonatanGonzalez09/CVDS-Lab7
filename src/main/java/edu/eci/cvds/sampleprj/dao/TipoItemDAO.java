package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.cvds.samples.entities.TipoItem;

public interface TipoItemDAO {

   public List<TipoItem> loadTiposItems() throws PersistenceException;

   public TipoItem loadTipoItem(int id) throws PersistenceException;

   public void insertTipoItem(String des) throws PersistenceException;

   
}