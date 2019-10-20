package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Item;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {
    
    
    public List<Item> consultarItems();        
    
    public Item consultarItem(@Param("idItm") int id);
    
    public void insertarItem(@Param("it")Item it);

    public List<Item> consultarItemsDisponibles();

    public void actulizarTarifaItem(@Param("idItem")int iditem, @Param("tarifaDia")long tarifa);


        
}
