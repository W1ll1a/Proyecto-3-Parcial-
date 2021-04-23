package hn.edu.ujcv.p3.Proyecto3.service.trabajadores;

import hn.edu.ujcv.p3.Proyecto3.entity.sedes.Sedes;
import hn.edu.ujcv.p3.Proyecto3.entity.trabajadores.Trabajadores;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.trabajadores.TrabajadoreRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrabajadoresService implements ITrabajadoresService{
    @Autowired
    private TrabajadoreRepositorio repository;
    public Trabajadores saveTrabajador(Trabajadores trabajador) throws BusinessException {
        try{
            return repository.save(trabajador);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Trabajadores> saveTrabajadores(List<Trabajadores> trabajadores) throws BusinessException{
        try{
            return repository.saveAll(trabajadores);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Trabajadores> getTrabajadores() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public Trabajadores getTrabajadorById(long id) throws BusinessException, NotFoundException {
        Optional<Trabajadores> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró Trabajador " + id);
        }
        return opt.get();
    }
    public Trabajadores getTrabajadorByNumerotelefono(long numeroTelefono) throws BusinessException, NotFoundException {
        Optional<Trabajadores> opt = null;
        try{
            opt = repository.findBynumerotelefono(numeroTelefono);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró Trabajador " + numeroTelefono);
        }
        return opt.get();
    }


    public void deleteTrabajador(long id)throws BusinessException, NotFoundException {
        Optional<Trabajadores> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró Trabajador " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }

    public Trabajadores updateTrabajador(Trabajadores trabajador)throws BusinessException,NotFoundException{
        Optional<Trabajadores> opt;
        try{
            opt = repository.findById(trabajador.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró trabajador " + trabajador.getId());
        }
        else{
            try {
                Trabajadores existingTrabajador=new Trabajadores();
                existingTrabajador.setId(trabajador.getId());
                existingTrabajador.setNombre(trabajador.getNombre());
                existingTrabajador.setNumerotelefono(trabajador.getNumerotelefono());
                existingTrabajador.setPuesto(trabajador.getPuesto());
                existingTrabajador.setFechainicio(trabajador.getFechainicio());
                existingTrabajador.setFechanacimiento(trabajador.getFechanacimiento());
                return repository.save(existingTrabajador);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }

        }
    }

}
