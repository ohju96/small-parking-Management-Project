package project.SPM.service;

import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserSaveForm;

public interface IUserService {

    int createUser(UserEntity userEntity) throws Exception;

    int checkUser(UserSaveForm userSaveForm) throws Exception;
}
