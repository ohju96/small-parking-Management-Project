package project.SPM.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserDTO;
import project.SPM.repository.IUserRepository;
import project.SPM.service.IUserService;

import javax.swing.text.html.parser.Entity;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository iUserRepository;

    @Override
    public int createUser(UserEntity userEntity) throws Exception {


        iUserRepository.save(userEntity);

        return 0;
    }
}
