package edu.eci.cvds.sampleprj.dao.mybatis;

import java.sql.Date;
import java.util.List;

import com.google.inject.Inject;

import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.guice.transactional.Transactional;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;

public class MyBATISClienteDAO implements ClienteDAO {

    @Inject
    private ClienteMapper ClienteMapper;

    @Override
    public Cliente loadCliente(long id) throws PersistenceException {
        try {
            return ClienteMapper.consultarCliente(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el Cliente " + id, e);
        }

    }

    @Override
    public List<Cliente> loadClientes() throws PersistenceException {
        try {
            return ClienteMapper.consultarClientes();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar todos los clientes", e);
        }

    }

    @Override
    @Transactional
    public void updateCliente(long id, Boolean vet) throws PersistenceException {
        try {
            ClienteMapper.updateCliente(id, vet);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al actualizar el cliente vetado.", e);
        }
    }

    @Override
    @Transactional
    public void registrarAlquilerCliente(Date fechainiciorenta, long docu, int item, Date fechafinrenta) throws PersistenceException {
        try {
            ClienteMapper.registrarAlquilerCliente(fechainiciorenta, docu, item, fechafinrenta);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al registrar Alquiler del cliente "+docu, e);
        }
    }

    @Override
    @Transactional
    public void insertCliente(Cliente cli) throws PersistenceException {
        try {
            ClienteMapper.insertCliente(cli);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al registrar el Cliente ", e);
        }

    }


}