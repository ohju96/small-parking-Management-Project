package project.SPM.mapper.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import project.SPM.dto.CarDTO;
import project.SPM.mapper.AbstractMongoDBComon;
import project.SPM.mapper.ICheckMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component("CheckMapper")
public class CheckMapper extends AbstractMongoDBComon implements ICheckMapper {
// 공통 기능 및 mongo 객체 사용을 위해 extends로 AbstractMongoDBComon을 상속 받는다.

    @Override
    public boolean saveTouchCheck(List<CarDTO> carDTOList, String colNm) throws Exception {

        log.debug("### CheckMapper saveTouchCheck Start ! : {}", this.getClass().getName());

        boolean res;

        if (carDTOList == null) {
            carDTOList = new LinkedList<>();
        }

        // 데이터를 저장할 컬렉션 생성
        super.createCollection(colNm, "checkTime");

        MongoCollection<Document> col = mongodb.getCollection(colNm);

        for (CarDTO carDTO : carDTOList) {
            if (carDTO == null) {
                carDTO = new CarDTO();
            }

            /// TODO: 2022-05-16 insertMany 알아보기
            // 한개씩 저장하기
            col.insertOne(new Document(new ObjectMapper().convertValue(carDTO, Map.class)));
        }

        res = true;

        log.debug("### CheckMapper saveTouchCheck Start ! : {}", this.getClass().getName());

        log.debug("### CheckMapper saveTouchCHeck Login End = res : {}", res);

        return res;
    }

    @Override
    public List<CarDTO> saveImgCheck() throws Exception {

        return null;
    }
}
