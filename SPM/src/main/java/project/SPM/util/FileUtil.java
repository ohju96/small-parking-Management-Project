package project.SPM.util;

import java.io.File;

public class FileUtil {

    public static String mkdirForDate(String uploadDir) {

        String path = uploadDir + DateUtil.getDateTime("/yyyy/MM/dd"); // 폴더

        File Folder = new File(path);

        if (!Folder.exists()) {
            Folder.mkdirs(); // 폴더를 생성
        }

        return path;
    }
}
