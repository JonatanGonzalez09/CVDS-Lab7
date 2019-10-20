package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import com.google.inject.Inject;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.samples.entities.ItemRentado;

public class MyBATISItemRentadoDAO implements ItemRentadoDAO {

    @Inject
    private ItemRentadoMapper ItemRentadoMapper;

    @Override
    public ItemRentado consultarItemRentado(int id) throws PersistenceException {
        try {
            return ItemRentadoMapper.consultarItemRentado(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el item " + id, e);
        }

    }

    @Override
    public List<ItemRentado> loadItemsCliente(long id) throws PersistenceException {
        try {
            return ItemRentadoMapper.consultarItemsCliente(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar los items rentados por el cliente "+id, e);
        }

    }

}