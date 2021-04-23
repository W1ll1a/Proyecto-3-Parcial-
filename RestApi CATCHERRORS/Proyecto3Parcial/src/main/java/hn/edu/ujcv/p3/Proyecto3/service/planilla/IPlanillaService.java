package hn.edu.ujcv.p3.Proyecto3.service.planilla;

import hn.edu.ujcv.p3.Proyecto3.entity.planilla.Planilla;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;

import java.util.List;

public interface IPlanillaService {
    Planilla       savePlanilla (Planilla planilla) throws BusinessException;
    List<Planilla> savePlanillas (List<Planilla> planillas) throws BusinessException;
    List<Planilla> getPlanillas() throws BusinessException;
    Planilla       getPlanillaById(long id) throws BusinessException, NotFoundException;
    Planilla       getPlanillaByName(String name)throws BusinessException, NotFoundException;
    void           deletePlanilla(long id)throws BusinessException, NotFoundException;
    Planilla       updatePlanilla(Planilla planilla)throws BusinessException,NotFoundException;
}
