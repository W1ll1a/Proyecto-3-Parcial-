package hn.edu.ujcv.p3.Proyecto3.service.trabajadores;

import hn.edu.ujcv.p3.Proyecto3.entity.registro.Registro;
import hn.edu.ujcv.p3.Proyecto3.entity.trabajadores.Trabajadores;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;

import java.util.List;

public interface ITrabajadoresService {
    Trabajadores            saveTrabajador (Trabajadores trabajador) throws BusinessException;
    List<Trabajadores>      saveTrabajadores (List<Trabajadores> trabajadores) throws BusinessException;
    List<Trabajadores>      getTrabajadores ()throws BusinessException;
    Trabajadores            getTrabajadorById (long id )throws BusinessException, NotFoundException;
    Trabajadores            getTrabajadorByNumerotelefono(long numerotelefono) throws BusinessException,NotFoundException;
    void                    deleteTrabajador(long Id)throws BusinessException,NotFoundException;
    Trabajadores            updateTrabajador(Trabajadores trabajador)throws BusinessException,NotFoundException;
}
