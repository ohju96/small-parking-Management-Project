package project.SPM.mapper.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
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

    // 엑셀 등록 로직
    @Override
    public void CreateCar(List<CarDTO> list) throws Exception {

        // TODO: 2022-05-11 차량 번호 중복 등록 처리 로직 구현하기
        //Document query = new Document();


        //mongo.createCollection("Car");
        // 저장할 컬렉션 객체 생성
        MongoCollection<Document> col = mongo.getCollection("Car");

        for (CarDTO carDTO : list) {
            if (carDTO == null) {
                carDTO = new CarDTO();
            }
          //  query.append("carNumber", carDTO.getCarNumber());

            //FindIterable<Document> documents = col.find(query);

            // DTO를 Map 데이터타입으로 변경 한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
            col.insertOne(new Document(new ObjectMapper().convertValue(carDTO, Map.class)));

        }

    }

    // 직접 등록 로직
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

    // 수정 및 삭제 로직
    @Override
    public boolean updateCar(List<CarDTO> list) throws Exception {

        log.debug("### CarMapper updateCar Start : {}", this.getClass().getName());

        boolean res = true;

        MongoCollection<Document> col = mongo.getCollection("Car");

        // 컬렉션이 존재할 경우에만 삭제한다.
        if (mongo.collectionExists("Car")) {
            mongo.dropCollection("Car");
        }

        for (CarDTO carDTO : list) {
            if (carDTO == null) {
                carDTO = new CarDTO();
            }

            col.insertOne(new Document(new ObjectMapper().convertValue(carDTO, Map.class)));
        }

        log.debug("### CarMapper updateCar End : {}", this.getClass().getName());
        return res;
    }

    @Override
    public boolean dropCar() throws Exception {

        boolean res = true;

        mongo.dropCollection("Car");

        return res;
    }
}
