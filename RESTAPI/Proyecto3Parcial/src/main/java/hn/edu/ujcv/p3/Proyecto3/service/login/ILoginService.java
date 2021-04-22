package hn.edu.ujcv.p3.Proyecto3.service.login;

import hn.edu.ujcv.p3.Proyecto3.entity.login.Login;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;


import java.util.List;

public interface ILoginService {
    Login             saveLog (Login login) throws BusinessException;
    List<Login>       savelogs (List<Login> logs) throws BusinessException;
    List<Login>       getUsuarios() throws BusinessException;
    Login             getUsuarioById(long id) throws BusinessException, NotFoundException;
    Login             getUsuarioByUsuario(String usuario)throws BusinessException, NotFoundException;
    void              deleteUsuario(long id)throws BusinessException, NotFoundException;
    Login             updateUsuario(Login cuenta)throws BusinessException,NotFoundException;


}
