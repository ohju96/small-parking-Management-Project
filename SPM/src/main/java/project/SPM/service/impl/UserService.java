package project.SPM.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserDTO;
import project.SPM.service.IUserService;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    @Override
    public int createUser(UserDTO userDTO) throws Exception {

        int res = 0;

        if (userDTO == null) {
            userDTO = new UserDTO();
        } else {
            res = 1;
        }

        return res;
    }
}
