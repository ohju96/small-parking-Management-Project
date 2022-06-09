package project.SPM.mapper.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import project.SPM.dto.CarDTO;
import project.SPM.dto.NoticeDTO;
import project.SPM.mapper.IManagementMapper;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component("ManagementMapper")
@RequiredArgsConstructor
public class ManagementMapper implements IManagementMapper {

    private final MongoTemplate mongo;

    // 공지사항 전송을 위해 주민 핸드폰 번호 리스트 가져오기
    @Override
    public List<CarDTO> getResidentPhoneNmList(NoticeDTO noticeDTO) throws Exception {

        List<CarDTO> carDTOList = new LinkedList<>();

        MongoCollection<Document> col = mongo.getCollection("Car");

        Document query = new Document();
        query.append("sort", "주민");
        query.append("userId", noticeDTO.getUserId());

        Document projection = new Document();
        projection.append("_id", 0);
        projection.append("name", 0);
        projection.append("carNumber", 0);
        projection.append("address", 0);
        projection.append("sort", 0);

        FindIterable<Document> documents = col.find(query).projection(projection);

        for (Document doc : documents) {

            if (doc == null) {
                doc = new Document();
            }

            CarDTO carDTO = new CarDTO();
            carDTO.setPhoneNumber(doc.getString("phoneNumber"));

            carDTOList.add(carDTO);
        }

        return carDTOList;
    }
}
