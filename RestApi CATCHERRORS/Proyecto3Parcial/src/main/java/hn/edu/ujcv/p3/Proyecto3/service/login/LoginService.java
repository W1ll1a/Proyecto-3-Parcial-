package hn.edu.ujcv.p3.Proyecto3.service.login;

import hn.edu.ujcv.p3.Proyecto3.entity.login.Login;
import hn.edu.ujcv.p3.Proyecto3.entity.planilla.Planilla;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.login.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService implements ILoginService{
    @Autowired
    private LoginRepository repository;

    public Login saveLog (Login login) throws BusinessException {
        try{
            return repository.save(login);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Login> savelogs(List<Login> logins) throws BusinessException {
        try{
            return repository.saveAll(logins);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Login> getUsuarios() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public Login getUsuarioById(long id) throws BusinessException, NotFoundException {
        Optional<Login> optional = null;
        try{
            optional = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!optional.isPresent()) {
            throw new NotFoundException("No se encontró Usuario con este Id " + id);
        }
        return optional.get();
    }
    public Login getUsuarioByUsuario(String usuario)throws BusinessException, NotFoundException {
        Optional<Login> optional = null;
        try{
            optional = repository.findByusuario(usuario);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!optional.isPresent()) {
            throw new NotFoundException("No se encontró Usuario con ese nombre : " + usuario);
        }
        return optional.get();
    }
    public void deleteUsuario(long id)throws BusinessException, NotFoundException {
        Optional<Login> optional = null;
        try{
            optional = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!optional.isPresent()) {
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
    public Login updateUsuario(Login cuenta)throws BusinessException,NotFoundException{
        Optional<Login> optional;
        try{
            optional = repository.findById(cuenta.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!optional.isPresent()) {
            throw new NotFoundException("No se encontró Planilla " + cuenta.getId());
        }
        else{
            try {
                Login existingUser=new Login();
                existingUser.setId(cuenta.getId());
                existingUser.setUsuario(cuenta.getUsuario());
                existingUser.setClave(cuenta.getClave());
                existingUser.setEmail(cuenta.getEmail());
                existingUser.setNumerotelefono(cuenta.getNumerotelefono());
                existingUser.setFechanacimiento(cuenta.getFechanacimiento());

                return repository.save(existingUser);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }

        }
    }
}
