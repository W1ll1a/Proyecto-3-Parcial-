package hn.edu.ujcv.p3.Proyecto3.service.registro;

import hn.edu.ujcv.p3.Proyecto3.entity.registro.Registro;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;

import java.util.List;

public interface IRegistroService {
    Registro        saveRegistro (Registro registro) throws BusinessException;
    List <Registro> saveRegistros (List<Registro> registro) throws BusinessException;
    List<Registro>  getRegistros ()throws BusinessException;
    Registro        getRegistroById (long id )throws BusinessException, NotFoundException;
    Registro        getRegistroByVehiculo(String vehiculo)throws BusinessException,NotFoundException;
    void            deleteRegistro(long Id)throws BusinessException,NotFoundException;
    Registro        updateRegistro(Registro registro)throws BusinessException,NotFoundException;
}
