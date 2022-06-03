package project.SPM.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.SPM.dto.UserDTO;
import project.SPM.repository.IUserRepository;
import project.SPM.repository.impl.UserRepository;
import project.SPM.service.IInfoService;

@Slf4j
@Service
@RequiredArgsConstructor
public class InfoService implements IInfoService {

    private final IUserRepository iUserRepository;
    private final UserRepository userRepository;

    @Override
    public UserDTO updateInfo(UserDTO userDTO) throws Exception {

        userRepository.findAll();

        return null;
    }
}
