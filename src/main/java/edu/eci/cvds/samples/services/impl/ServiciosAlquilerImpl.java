package edu.eci.cvds.samples.services.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

   @Inject
   private ItemDAO itemDAO;

   @Inject
   private ClienteDAO clienteDAO;

   @Inject
   private TipoItemDAO tipoItemDAO;

   @Inject
   private ItemRentadoDAO itemRentadoDAO;

   @Override
   public int valorMultaRetrasoxDia(int itemId) throws ExcepcionServiciosAlquiler {
        try {
           return (int) itemDAO.loadItem(itemId).getTarifaxDia();
        } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el valor la multa para el item "+itemId, ex);
        }
   }

   @Override
   public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
       try {
           return clienteDAO.loadCliente(docu);
        } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el cliente "+docu, ex);
        }
   }

   @Override
   public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
       try {
           return  itemRentadoDAO.loadItemsCliente(idcliente);
        } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar los items rentados por el cliente ", ex);
        }
   }

   @Override
   public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
       try {
           return clienteDAO.loadClientes();
        } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar los clientes ", ex);
        }
   }
   
   @Override
   public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
       try {
           return itemDAO.loadItem(id);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
       }
   }

   @Override
   public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler {
       try {
           return itemDAO.loadItemsDisponibles();
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar Items Disponibles",ex);
       }
   }

   @Override
   public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
       Long result = (long) 0;
       try {
           ItemRentado it= itemRentadoDAO.consultarItemRentado(iditem);
           Date fechafin =it.getFechafinrenta();
           int comp = fechafin.compareTo(fechaDevolucion);
           if (comp > 0){
               Long day = (fechaDevolucion.getTime()-fechafin.getTime())/(1000 * 60 * 60 * 24);
               result = (itemDAO.loadItem(iditem).getTarifaxDia())*day;
            }
            
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar Items Disponibles",ex);
        }
        return result;
   }

   @Override
   public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
       try {
           return tipoItemDAO.loadTipoItem(id);
        } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el tipo de Item "+id, ex);
        }
   }

   @Override
   public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
       try {
           return tipoItemDAO.loadTiposItems();
        } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar los tipos de Items ", ex);
        }
   }

   @Override
   public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
       Calendar calendario= Calendar.getInstance();
       calendario.setTime(date);
       calendario.add(Calendar.DAY_OF_YEAR, numdias);
       Date fechaFin= (Date) calendario.getTime();
        try {
            // Revisar formato de Fecha
           clienteDAO.registrarAlquilerCliente(date, docu, item.getId(), fechaFin);
       } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar Alquilier Cliente",ex);
       }   
   }

   @Override
   public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
       try {
           clienteDAO.insertCliente(c);
       } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar Cliente",ex);
       }
       
   }

   @Override
   public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
       try {
           long tarifa = itemDAO.loadItem(iditem).getTarifaxDia();
           return tarifa*numdias;
        } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el Costo del Alquiler", ex);
        }
   }

   @Override
   public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
       try {
           itemDAO.updateTarifaitem(id, tarifa);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al actualizar tarifa del item "+id,ex);
       }
   }
   @Override
   public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
       try {
           itemDAO.insertItem(i);
        } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al registrar el Item", ex);
        }
   }

   @Override
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
       try {
           clienteDAO.updateCliente(docu, estado);
        } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al cambiar estado del cliente "+docu, ex);
        }
   }
}