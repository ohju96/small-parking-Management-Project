package project.SPM.service;

import org.springframework.web.multipart.MultipartFile;

public interface ICarService {

    void CreateCar(MultipartFile mf) throws Exception;
}
