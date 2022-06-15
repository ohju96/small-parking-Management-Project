package project.SPM.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

@Slf4j
public class UrlUtil {

    private String readAll(Reader rd) {
        log.debug("UrlUtil: readAll 시작");
        StringBuilder sb =null;

        try {
            sb = new StringBuilder();
            int cp =0;

            while((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }

        } catch (Exception e) {
            log.debug("readAll Exception : " + e.toString());
        }

        log.debug("UrlUtil: readAll 종료");
        return sb.toString();
    }

    public String urlReadforString(String url) throws IOException {

        log.info("UrlUtil: urlReadforString 시작");
        log.info("UrlUtil: urlReadforString url :" + url);

        BufferedReader rd =null;
        InputStream is =null;


        String res ="";

        try {
            is = new URL(url).openStream();

            rd= new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            res = readAll(rd);

        }catch(Exception e) {
            log.info("urlReadforString Exception :" + e.toString());
        } finally {
            is.close();
            is = null;
            rd = null;
        }
        log.info("UrlUtil: urlReadforString 종료");

        return res ;
    }
}