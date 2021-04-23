package hn.edu.ujcv.p3.Proyecto3.service.registro;

import hn.edu.ujcv.p3.Proyecto3.entity.registro.Registro;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.registro.RegistroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroService implements IRegistroService {
    @Autowired
    private RegistroRepositorio repositorio;
    public Registro saveRegistro (Registro registro) throws BusinessException {
        try{
            return repositorio.save(registro);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public List<Registro> saveRegistros (List<Registro> registro) throws BusinessException{
        try{
            return repositorio.saveAll(registro);
        }catch (Exception e ){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Registro> getRegistros()throws BusinessException{
        try{
            return repositorio.findAll();
        }catch (Exception e ){
            throw new BusinessException(e.getMessage());
        }
    }
    public  Registro getRegistroById(long id)throws BusinessException,NotFoundException {
        Optional<Registro> opctionalRegistro = null;
        try {
            opctionalRegistro = repositorio.findById(id);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (!opctionalRegistro.isPresent()){
            throw new NotFoundException("No se encontro Registro " + id);
    }
        return opctionalRegistro.get();
        }

    public Registro getRegistroByVehiculo(String vehiculo) throws BusinessException, NotFoundException {
        Optional<Registro> opt = null;
        try {
            opt = repositorio.findByvehiculo(vehiculo);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró sede " + vehiculo);
        }
        return opt.get();
    }

    public void deleteRegistro (long Id)throws BusinessException,NotFoundException {
        Optional<Registro> optionalRegistro = null;
        try{
            optionalRegistro = repositorio.findById(Id);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!optionalRegistro.isPresent()){
            throw new NotFoundException("No se encontro este Registro " + Id);
        }else
            try{
                repositorio.deleteById(Id);
            }catch (Exception e2){
                throw new BusinessException(e2.getMessage());
            }
    }

    public Registro updateRegistro (Registro registro)throws BusinessException , NotFoundException {
        Optional<Registro> opt;
        try {
            opt = repositorio.findById(registro.getId());
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());

        }
        if (!opt.isPresent()) {
            throw new NotFoundException("Error, no se encontro" + registro.getId());
        } else {


            try {
                Registro existingRegistro = new Registro();
                existingRegistro.setVehiculo(registro.getVehiculo());
                existingRegistro.setFechasalida(registro.getFechasalida());
                existingRegistro.setFechaentrada(registro.getFechaentrada());
                existingRegistro.setFechamaximaentrega(registro.getFechamaximaentrega());
                existingRegistro.setCantidadproducto(registro.getCantidadproducto());

                return repositorio.save(existingRegistro);

            } catch (Exception e1) {
                throw new BusinessException(e1.getMessage());
            }

        }
    }
    }


