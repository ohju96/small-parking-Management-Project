package project.SPM.mapper.impl;

import antlr.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import project.SPM.dto.CarDTO;
import project.SPM.dto.UserDTO;
import project.SPM.dto.ViewCarDTO;
import project.SPM.mapper.ICheckMapper;

import java.util.*;

@Slf4j
@Component("CheckMapper")
@RequiredArgsConstructor
public class CheckMapper implements ICheckMapper {
// 공통 기능 및 mongo 객체 사용을 위해 extends로 AbstractMongoDBComon을 상속 받는다.

    private final MongoTemplate mongo;

    // 직접 저장 로직
    @Override
    public boolean saveTouchCheck(List<CarDTO> list, String colNm) throws Exception {

        log.debug("### CheckMapper saveTouchCheck Start ! : {}", this.getClass().getName());

        boolean res;

        // 데이터를 저장할 컬렉션 생성
        mongo.createCollection(colNm);

        log.debug("### CheckMapper saveTouchCheck colNm : {}", colNm);

        MongoCollection<Document> col = mongo.getCollection(colNm);

        for (CarDTO carDTO : list) {

            if (carDTO == null) {
                carDTO = new CarDTO();
            }

            log.debug("### CheckMapper saveTouchCheck carDTO : {}", carDTO);

            col.insertOne(new Document(new ObjectMapper().convertValue(carDTO, Map.class)));
        }

        // 한개씩 저장하기

        res = true;


        log.debug("### CheckMapper saveTouchCHeck Login End = res : {}", res);
        log.debug("### CheckMapper saveTouchCheck End ! : {}", this.getClass().getName());

        return res;
    }

    // 이미지 저장 로직
    @Override
    public List<CarDTO> saveImgCheck() throws Exception {

        return null;
    }

    // 완료 항목 보기
    @Override
    public List<ViewCarDTO> viewCheck(UserDTO userDTO) {

        List<ViewCarDTO> viewCarDTOList = new LinkedList<>();

        for (String colNm : mongo.getCollectionNames()) {

            if (colNm == null) {
                colNm = new String();
            }

            // 컬렉션 명을 _ 기준으로 잘라 String 배열에 담는다.
            String res[] = colNm.split("_");

            // String 배열 중 ID값이 있는 부분과, 세션의 id값을 각 String 변수에 담는다.
            String result = res[0];
            String sessionId = userDTO.getUserId();

            // 두 String을 비교하여 같을 시 리스트에 담아준다.
            if (result.equals(sessionId)) {

                ViewCarDTO viewCarDTO = new ViewCarDTO();
                viewCarDTO.setCheckCollectionName(colNm);
                viewCarDTOList.add(viewCarDTO);
            }
        }
        return viewCarDTOList;

    }
}
