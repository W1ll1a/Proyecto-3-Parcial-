package hn.edu.ujcv.p3.Proyecto3.service.vehiculo;

import hn.edu.ujcv.p3.Proyecto3.entity.vehiculos.Vehiculos;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.vehiculos.VehiculosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService implements IVehiculoService{
    @Autowired
    private VehiculosRepositorio repository;
    public Vehiculos saveVehiculo(Vehiculos vehiculo) throws BusinessException {
        try{
            return repository.save(vehiculo);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Vehiculos> saveVehiculos(List<Vehiculos> vehiculos) throws BusinessException{
        try{
            return repository.saveAll(vehiculos);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Vehiculos> getVehiculos() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public Vehiculos getVehiculoById(long id) throws BusinessException, NotFoundException {
        Optional<Vehiculos> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró vehiculo" + id);
        }
        return opt.get();
    }
    public Vehiculos getVehiculoByPlaca (String placa) throws BusinessException,NotFoundException{
        Optional<Vehiculos> optionalVehiculos = null;
        try{
            optionalVehiculos = repository.findByplaca(placa);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!optionalVehiculos.isPresent()){
            throw new NotFoundException("Error, no se encontro " + placa);
        }
        return optionalVehiculos.get();
    }
    public void deleteVehiculo(long id)throws BusinessException, NotFoundException {
        Optional<Vehiculos> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró Vehiculo " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Vehiculos updateVehiculo(Vehiculos vehiculo)throws BusinessException,NotFoundException{
        Optional<Vehiculos> opt;
        try{
            opt = repository.findById(vehiculo.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró Vehiculo " + vehiculo.getId());
        }
        else{
            try {
                Vehiculos existingVehiculo=new Vehiculos();
                existingVehiculo.setId(vehiculo.getId());
                existingVehiculo.setTipodevehiculo(vehiculo.getTipodevehiculo());
                existingVehiculo.setMarcavehiculo(vehiculo.getMarcavehiculo());
                existingVehiculo.setPlaca(vehiculo.getPlaca());
                existingVehiculo.setColor(vehiculo.getColor());
                existingVehiculo.setEstado(vehiculo.getEstado());
                return repository.save(existingVehiculo);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }

        }
    }

}
