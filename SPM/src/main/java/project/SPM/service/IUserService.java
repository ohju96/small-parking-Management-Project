package project.SPM.service;

import project.SPM.Entity.UserEntity;

public interface IUserService {

    int createUser(UserEntity userEntity) throws Exception;
}
