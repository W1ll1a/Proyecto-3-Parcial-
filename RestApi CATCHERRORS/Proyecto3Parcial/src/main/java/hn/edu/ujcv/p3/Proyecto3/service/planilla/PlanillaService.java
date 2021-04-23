package hn.edu.ujcv.p3.Proyecto3.service.planilla;

import hn.edu.ujcv.p3.Proyecto3.entity.planilla.Planilla;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.planilla.PlanillaRepository;
import hn.edu.ujcv.p3.Proyecto3.service.planilla.IPlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanillaService implements IPlanillaService {
    @Autowired
    private PlanillaRepository repository;
    public Planilla savePlanilla (Planilla planilla) throws BusinessException {
        try{
            return repository.save(planilla);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Planilla> savePlanillas(List<Planilla> planillas) throws BusinessException {
        try{
            return repository.saveAll(planillas);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Planilla> getPlanillas() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public Planilla getPlanillaById(long id) throws BusinessException, NotFoundException {
        Optional<Planilla> optionalPlanilla = null;
        try{
            optionalPlanilla = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!optionalPlanilla.isPresent()) {
            throw new NotFoundException("No se encontró planilla con este Id " + id);
        }
        return optionalPlanilla.get();
    }
    public Planilla getPlanillaByName(String name)throws BusinessException, NotFoundException {
        Optional<Planilla> optionalPlanilla = null;
        try{
            optionalPlanilla = repository.findByname(name);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!optionalPlanilla.isPresent()) {
            throw new NotFoundException("No se encontró planilla con ese nombre : " + name);
        }
        return optionalPlanilla.get();
    }
    public void deletePlanilla(long id)throws BusinessException, NotFoundException {
        Optional<Planilla> optionalPlanilla = null;
        try{
            optionalPlanilla = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!optionalPlanilla.isPresent()) {
            throw new NotFoundException("No se encontró planilla con esa id " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Planilla updatePlanilla(Planilla planilla)throws BusinessException,NotFoundException{
        Optional<Planilla> opt;
        try{
            opt = repository.findById(planilla.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró Planilla " + planilla.getId());
        }
        else{
            try {
                Planilla existingPlanilla=new Planilla();
                existingPlanilla.setId(planilla.getId());
                existingPlanilla.setName(planilla.getName());
                existingPlanilla.setTotalporhora(planilla.getTotalporhora());
                existingPlanilla.setHorastrabajadas(planilla.getHorastrabajadas());
                existingPlanilla.setHorasextra(planilla.getHorasextra());
                existingPlanilla.setTotalpagar(planilla.getTotalpagar());

                return repository.save(existingPlanilla);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }

        }
    }

}
