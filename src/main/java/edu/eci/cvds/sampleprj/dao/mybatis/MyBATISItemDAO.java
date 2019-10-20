package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import com.google.inject.Inject;

import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.guice.transactional.Transactional;

import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.Item;

public class MyBATISItemDAO implements ItemDAO {

    @Inject
    private ItemMapper itemMapper;

    @Override
    public List<Item> loadItems() throws PersistenceException {
        try {
            return itemMapper.consultarItems();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar todos los Items", e);
        }
    }

    @Override
    public Item loadItem(int id) throws PersistenceException {
        try {
            return itemMapper.consultarItem(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el item " + id, e);
        }
    }

    @Override
    @Transactional
    public void insertItem(Item it) throws PersistenceException {
        try {
            itemMapper.insertarItem(it);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al insertar el item "+ it, e);
        }
    }

    @Override
    public List<Item> loadItemsDisponibles() throws PersistenceException {
        try {
            return itemMapper.consultarItemsDisponibles();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar los items disponibles", e);
        }

    }

    @Override
    @Transactional
    public void updateTarifaitem(int id, long tarifa) throws PersistenceException {
        try {
            itemMapper.actulizarTarifaItem(id, tarifa);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al actualizar la tarifa del item "+id, e);
        }

    }

}