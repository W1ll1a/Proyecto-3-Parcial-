package hn.edu.ujcv.p3.Proyecto3.service.vehiculo;

import hn.edu.ujcv.p3.Proyecto3.entity.vehiculos.Vehiculos;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;

import java.util.List;

public interface IVehiculoService {
    Vehiculos               saveVehiculo (Vehiculos vehiculo) throws BusinessException;
    List<Vehiculos>         saveVehiculos (List<Vehiculos> vehiculos) throws BusinessException;
    List<Vehiculos>         getVehiculos ()throws BusinessException;
    Vehiculos               getVehiculoById (long id )throws BusinessException, NotFoundException;
    Vehiculos               getVehiculoByPlaca (String placa) throws BusinessException, NotFoundException;
    void                    deleteVehiculo(long Id)throws BusinessException,NotFoundException;
    Vehiculos               updateVehiculo(Vehiculos vehiculo)throws BusinessException,NotFoundException;
}
