package project.SPM.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.SPM.Entity.UserEntity;
import project.SPM.repository.IUserRepository;
import project.SPM.service.IUserService;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository iUserRepository;

    @Override
    public int createUser(UserEntity userEntity) throws Exception {

        int res = 0;

        iUserRepository.save(userEntity);

        if (userEntity != null) {
            res = 1;
        } else {
            res = 0;
        }

        log.info("서비스에서 체크 res ={}", res);

        return res;
    }
}
