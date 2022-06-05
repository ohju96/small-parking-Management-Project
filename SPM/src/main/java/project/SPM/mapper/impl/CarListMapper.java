package project.SPM.mapper.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import project.SPM.dto.CarDTO;
import project.SPM.dto.UserDTO;
import project.SPM.mapper.ICarListMapper;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component("CarListMapper")
@RequiredArgsConstructor
public class CarListMapper implements ICarListMapper {

    private final MongoTemplate mongo;

    // 전체 차량 조회
    @Override
    public List<CarDTO> getFullCarList(UserDTO userDTO) throws Exception {

        // 조회 결과를 전달하기 위한 객체
        List<CarDTO> carDTOList = new LinkedList<>();

        MongoCollection<Document> col = mongo.getCollection("Car");

        // 조회할 조건, SQL의 WHERE 역할과 같다.
        Document query = new Document();
        query.append("userId", userDTO.getUserId());

        Document projection = new Document();

        // ObjectId를 가지고 오지 않을 때 사용
        projection.append("_id", 0);

        FindIterable<Document> documents = col.find(query).projection(projection);

        for (Document doc : documents) {
            if (doc == null) {
                doc = new Document();
            }

            CarDTO carDTO = new CarDTO();

            carDTO.setName(doc.getString("name"));
            carDTO.setPhoneNumber(doc.getString("phoneNumber"));
            carDTO.setCarNumber(doc.getString("carNumber"));
            carDTO.setAddress(doc.getString("address"));
            carDTO.setSort(doc.getString("sort"));

            carDTOList.add(carDTO);

        }

        return carDTOList;
    }

    // 주민 차량 조회
    @Override
    public List<CarDTO> getResidentList(UserDTO userDTO) throws Exception {

        // 조회 결과를 전달하기 위한 객체 생성하기
        LinkedList<CarDTO> carDTOList = new LinkedList<>();

        MongoCollection<Document> col = mongo.getCollection("Car");

        // 조회할 조건, SQL의 WHERE 역할과 같다.
        Document query = new Document();
        query.append("sort", "주민");
        query.append("userId", userDTO.getUserId());

        // 조회 결과 중 출력할 컬럼
        Document projection = new Document();
        // ObjectId를 가져오지 않기 위해 사용한다.
        projection.append("_id", 0);

        FindIterable<Document> documents = col.find(query).projection(projection);

        for (Document doc : documents) {
            if (doc == null) {
                doc = new Document();
            }

            CarDTO carDTO = new CarDTO();

            carDTO.setName(doc.getString("name"));
            carDTO.setPhoneNumber(doc.getString("phoneNumber"));
            carDTO.setCarNumber(doc.getString("carNumber"));
            carDTO.setAddress(doc.getString("address"));
            carDTO.setSort(doc.getString("sort"));

            carDTOList.add(carDTO);

        }

        return carDTOList;
    }

    @Override
    public List<CarDTO> getVisitList(UserDTO userDTO) throws Exception {

        LinkedList<CarDTO> carDTOList = new LinkedList<>();

        MongoCollection<Document> col = mongo.getCollection("Car");

        // 조회할 조건, SQL의 WHERE 역할
        Document query = new Document();
        query.append("sort", "방문자");
        query.append("userId", userDTO.getUserId());

        // 조회 결과 중 출력할 컬럼
        Document projection = new Document();
        // ObjectId를 가져오지 않기 위해 사용한다.
        projection.append("_id", 0);

        FindIterable<Document> documents = col.find(query).projection(projection);

        for (Document doc : documents) {
            if (doc == null) {
                doc = new Document();
            }

            CarDTO carDTO = new CarDTO();

            carDTO.setName(doc.getString("name"));
            carDTO.setPhoneNumber(doc.getString("phoneNumber"));
            carDTO.setCarNumber(doc.getString("carNumber"));
            carDTO.setAddress(doc.getString("address"));
            carDTO.setSort(doc.getString("sort"));

            carDTOList.add(carDTO);

        }

        return carDTOList;
    }

    @Override
    public List<CarDTO> getBlackList(UserDTO userDTO) throws Exception {
        LinkedList<CarDTO> carDTOList = new LinkedList<>();

        MongoCollection<Document> col = mongo.getCollection("Car");

        // 조회할 조건, SQL의 WHERE 역할
        Document query = new Document();
        query.append("sort", "무단");
        query.append("userId", userDTO.getUserId());

        // 조회 결과 중 출력할 컬럼
        Document projection = new Document();
        // ObjectId를 가져오지 않기 위해 사용한다.
        projection.append("_id", 0);

        FindIterable<Document> documents = col.find(query).projection(projection);

        for (Document doc : documents) {
            if (doc == null) {
                doc = new Document();
            }

            CarDTO carDTO = new CarDTO();

            carDTO.setName(doc.getString("name"));
            carDTO.setPhoneNumber(doc.getString("phoneNumber"));
            carDTO.setCarNumber(doc.getString("carNumber"));
            carDTO.setAddress(doc.getString("address"));
            carDTO.setSort(doc.getString("sort"));

            carDTOList.add(carDTO);

        }

        return carDTOList;
    }
}
