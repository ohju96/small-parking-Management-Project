package project.SPM.service;

import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserDTO;

public interface IUserService {

    void createUser(UserEntity userEntity) throws Exception;
}
