package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import com.google.inject.Inject;

import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.guice.transactional.Transactional;

import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;

public class MyBATISTipoItemDAO implements TipoItemDAO {

    @Inject
    private TipoItemMapper tipoItemMapper;

    @Override
    public List<TipoItem> loadTiposItems() throws PersistenceException {
        try {
            return tipoItemMapper.getTiposItems();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar todos los tipos de Item", e);
        }
    }

    @Override
    public TipoItem loadTipoItem(int id) throws PersistenceException {
        try {
            return tipoItemMapper.getTipoItem(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el tipos de Item "+ id , e);
        }
    }

    @Override
    @Transactional
    public void insertTipoItem(String des) throws PersistenceException {
        try {
            tipoItemMapper.addTipoItem(des);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al insertat el tipo de Item " + des , e);
        }
    }

}