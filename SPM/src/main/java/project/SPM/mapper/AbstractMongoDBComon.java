package project.SPM.mapper;

import com.mongodb.client.model.Indexes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractMongoDBComon {

    protected MongoTemplate mongodb;

    /**
     * 컬렉션 생성하기
     * @param colNm 생성할 컬렉션 명
     * @return 생성 결과
     */
    protected boolean createCollection(String colNm) {

        boolean res;

        if (mongodb.collectionExists(colNm)) {
            res = false;
        } else {
            mongodb.createCollection(colNm);
            res = true;
        }

        return res;
    }

    /**
     *  인덱스 컬럼이 한 개일 때 컬렉션 생성
     * @param colNm 생성할 컬렉션명
     * @param index 생성할 인덱스
     * @return 생성 결과
     */
    protected boolean createCollection(String colNm, String index) {

        String[] indexArr = {index};

        return createCollection(colNm, indexArr);
    }

    /**
     * 인덱스 컬럼이 여러 개일 때 컬렉션 생성
     * @param colNm 생성할 컬렉션명
     * @param index 생성할 인덱스
     * @return 생성결과
     */
    protected boolean createCollection(String colNm, String[] index) {

        log.debug("### .createCollection start! : {}", this.getClass().getName());

        boolean res = false;

        // 기존에 등록된 컬렉션 이름이 존재하는지 체크 후 없다면 생성
        if (!mongodb.collectionExists(colNm)) {

            // 인덱스 값이 존재하는 경우
            if (index.length > 0) {
                // 컬렉션 생성 및 인덱스 생성
                mongodb.createCollection(colNm).createIndex(Indexes.ascending(index));
            } else {
                // 인덱스가 없으면 인덱스 없이 컬렉션 생성
                mongodb.createCollection(colNm);
            }
            log.debug("### .createCollection End! : {}", this.getClass().getName());
            res = true;
        }

        return res;
    }

    /**
     * 컬렉션 삭제
     * @param colNm 삭제할 컬렉션명
     * @return 삭제 결과
     */
    protected boolean dropCollection(String colNm) {

        boolean res = false;

        if (mongodb.collectionExists(colNm)) {
            mongodb.dropCollection(colNm);
            res = true;
        }

        return res;
    }

}
