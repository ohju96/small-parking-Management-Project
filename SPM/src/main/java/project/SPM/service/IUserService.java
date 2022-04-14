package project.SPM.service;

import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserDTO;
import project.SPM.dto.UserSaveForm;

public interface IUserService {


    void InsertUser(UserDTO userDTO) throws Exception;
}
