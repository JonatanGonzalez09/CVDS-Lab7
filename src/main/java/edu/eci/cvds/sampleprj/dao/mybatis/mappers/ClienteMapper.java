package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Cliente;

/**
 *
 * @author 2106913
 */
public interface ClienteMapper {
    
    public Cliente consultarCliente(@Param("idcli") long id);

    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin 
     */
    public void agregarItemRentadoACliente(@Param("idcli") int id, 
            @Param("itemId") int idit, 
            @Param("fechainiciorenta") Date fechainicio,
            @Param("fechafinrenta") Date fechafin);

    /**
     * Consultar todos los clientes
     * @return 
     */
    public List<Cliente> consultarClientes();

    /**
     *  Actualizar Cliente Vetado
     */
    public void updateCliente(@Param("idcli")long id , @Param("Cvetado") Boolean vet);

    /**
     * Registrar alquiler Cliente
     */
    public void registrarAlquilerCliente(@Param("fechainiciorenta")Date fechainiciorenta , @Param("idcli")long docu, @Param("itemId")int item, @Param("fechafinrenta")Date fechafinrenta);

    /**
     * Registrar Cliente
     */
    public void insertCliente(@Param("cli") Cliente cli);
}
