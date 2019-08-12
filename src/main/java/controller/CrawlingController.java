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

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Controller
public class CrawlingController {
    @ResponseBody
    @GetMapping("/crawling/news/{keyword}")
    public List<Map<String, String>> getCrawlingResult(@PathVariable("keyword") String keyword) throws IOException {
        List<Map<String, String>> results = new ArrayList<>();

        String targetUrl = "https://search.naver.com/search.naver?query=" + keyword + "&where=news";
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
