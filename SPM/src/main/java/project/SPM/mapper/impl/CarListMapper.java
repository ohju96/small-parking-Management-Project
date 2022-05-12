package project.SPM.mapper.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import project.SPM.dto.CarDTO;
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
    public List<CarDTO> getFullCarList() throws Exception {

        // 조회 결과를 전달하기 위한 객체
        List<CarDTO> carDTOList = new LinkedList<>();

        MongoCollection<Document> col = mongo.getCollection("Car");

        Document projection = new Document();

        // ObjectId를 가지고 오지 않을 때 사용
        projection.append("_id", 0);

        FindIterable<Document> documents = col.find().projection(projection);

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
