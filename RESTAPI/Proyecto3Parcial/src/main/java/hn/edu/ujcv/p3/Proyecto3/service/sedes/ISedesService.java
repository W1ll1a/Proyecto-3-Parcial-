package hn.edu.ujcv.p3.Proyecto3.service.sedes;


import hn.edu.ujcv.p3.Proyecto3.entity.sedes.Sedes;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;

import java.util.List;

public interface ISedesService {
    Sedes           saveSede (Sedes sede) throws BusinessException;
    List<Sedes>     saveSedes (List<Sedes> Sedes) throws BusinessException;
    List<Sedes>     getSedes ()throws BusinessException;
    Sedes           getSedeById (long id )throws BusinessException, NotFoundException;
    Sedes           getSedeBynombredesede(String sede) throws BusinessException,NotFoundException;
    void            deleteSede(long Id)throws BusinessException,NotFoundException;
    Sedes           updateSede(Sedes sede)throws BusinessException,NotFoundException;
}
