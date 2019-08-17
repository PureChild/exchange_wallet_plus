package controller;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * 환율 이슈 크롤링 레스트컨트롤러 클래스
 * @author 이승수
 */
@RestController
public class CrawlingController {
    /**
     * @param keyword 검색 키워드
     * @return 기사 목록(thumb : 썸네일 이미지, title : 기사 제목)
     * @throws IOException
     */
    @GetMapping("/crawling/news/{keyword}")
    public List<Map<String, String>> getCrawlingResult(@PathVariable("keyword") String keyword) throws IOException {
        List<Map<String, String>> results = new ArrayList<>();

        String targetUrl = "https://search.naver.com/search.naver?where=news&query=환율" + keyword;
        Connection connection = Jsoup.connect(targetUrl);
        Document document = Jsoup.parse(new String(connection.execute().bodyAsBytes(),"UTF-8"));
        Elements elements = document.select(".type01 li");
        Elements thumbs = elements.select(".thumb");
        Elements titles = elements.select("dt");

        for(int i = 0; i < 5; i++){
            Map<String, String> result = new HashMap<>();
            result.put("thumb", thumbs.get(i).toString());
            result.put("title", titles.get(i).toString());

            results.add(result);
        }

        return results;
    }
}
