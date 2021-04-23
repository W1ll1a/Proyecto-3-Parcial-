package hn.edu.ujcv.p3.Proyecto3.service.sedes;

import hn.edu.ujcv.p3.Proyecto3.entity.sedes.Sedes;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.sedes.SedesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SedesService implements ISedesService {
    @Autowired
    private SedesRepositorio repositorio;

    public Sedes saveSede(Sedes sede) throws BusinessException {
        try {
            return repositorio.save(sede);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public List<Sedes> saveSedes(List<Sedes> sedes) throws BusinessException {
        try {
            return repositorio.saveAll(sedes);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public List<Sedes> getSedes() throws BusinessException {
        try {
            return repositorio.findAll();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public Sedes getSedeById(long id) throws BusinessException, NotFoundException {
        Optional<Sedes> opt = null;
        try {
            opt = repositorio.findById(id);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el sede " + id);
        }
        return opt.get();
    }
    public Sedes getSedeBynombredesede(String sede) throws BusinessException, NotFoundException {
        Optional<Sedes> opt = null;
        try {
            opt = repositorio.findBynombredesede(sede);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el sede " + sede);
        }
        return opt.get();
    }

      public void deleteSede(long id) throws BusinessException, NotFoundException {
        Optional<Sedes> opt = null;
        try {
            opt = repositorio.findById(id);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró Sede " + id);
        } else {
            try {
                repositorio.deleteById(id);
            } catch (Exception e1) {
                throw new BusinessException(e1.getMessage());
            }
        }


    }
    public Sedes updateSede(Sedes sede)throws BusinessException,NotFoundException{
        Optional<Sedes> opt;
        try{
            opt = repositorio.findById(sede.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el producto " + sede.getId());
        }
        else{
            try {
                Sedes existingSede=new Sedes();
                existingSede.setId(sede.getId());
                existingSede.setCiudad(sede.getCiudad());
                existingSede.setCantidaddevehiculosensede(sede.getCantidaddevehiculosensede());
                existingSede.setNombredirector(sede.getNombredirector());
                existingSede.setNombredesede(sede.getNombredesede());
                existingSede.setDireccion(sede.getDireccion());
                return repositorio.save(existingSede);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }

        }
    }
}