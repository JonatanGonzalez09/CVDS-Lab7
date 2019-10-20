package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.ItemRentado;

/**
 *
 */
public interface ItemRentadoMapper {
    public ItemRentado consultarItemRentado(@Param("idItemR") int id);

    public List<ItemRentado> consultarItemsCliente(@Param("idcli")long idcliente);

}
