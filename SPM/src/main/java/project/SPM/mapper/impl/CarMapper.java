package project.SPM.mapper.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import project.SPM.dto.CarDTO;
import project.SPM.mapper.ICarMapper;

import java.util.List;
import java.util.Map;

@Slf4j
@Component("CarMapper")
@RequiredArgsConstructor
public class CarMapper implements ICarMapper {

    private final MongoTemplate mongo;

    @Override
    public void CreateCar(List<CarDTO> list) throws Exception {

        //mongo.createCollection("Car");
        // 저장할 컬렉션 객체 생성
        MongoCollection<Document> col = mongo.getCollection("Car");

        for (CarDTO carDTO : list) {
            if (carDTO == null) {
                carDTO = new CarDTO();
            }

            // DTO를 Map 데이터타입으로 변경 한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
            col.insertOne(new Document(new ObjectMapper().convertValue(carDTO, Map.class)));
        }

    }

    @Override
    public boolean addCar(CarDTO carDTO) throws Exception {

        log.debug("#### Mapper carDTO : {}", carDTO);
        boolean res = true;

        MongoCollection<Document> col = mongo.getCollection("Car");

        if (carDTO == null) {
            carDTO = new CarDTO();
            res = false;
        }
        col.insertOne(new Document(new ObjectMapper().convertValue(carDTO, Map.class)));


        log.debug("#### resturn : res : {}", res);

        return res;
    }
}
