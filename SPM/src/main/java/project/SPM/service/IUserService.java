package project.SPM.service;

import project.SPM.dto.UserDTO;

public interface IUserService {

    int createUser(UserDTO userDTO) throws Exception;
}
