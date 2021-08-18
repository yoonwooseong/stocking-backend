package bis.stock.back.domain.stock;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import bis.stock.back.domain.stock.dto.Stock;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StockService {

   private final StockRepository stockRepository;

   @PersistenceContext
   private EntityManager em;

   @PostConstruct // init
   public void stockListUpdate() throws UnsupportedEncodingException {

      // 만약 테이블이 비어있으면 실행하기. 안비어있으면 그냥 넘어감
      if(totalList().size() != 0) {
         return;
      }

      // resource에서 input.csv(다운받아서 넣어놈)을 읽어와서 Stock객체의 배열로 변환함
      InputStream is = getClass().getResourceAsStream("/input.csv");
      List<Stock> stockList = new CsvToBeanBuilder<Stock>(new InputStreamReader(is, "EUC-KR"))
              .withType(Stock.class)
              .build()
              .parse();

      // 한꺼번에 저장
      stockRepository.saveAll(stockList);
   }

   public List<Stock> totalList() {

      return em.createQuery("select s from Stock s", Stock.class)
            .getResultList();
   }

   public String findcode(String itemname) {

      return em.createQuery("select s from Stock s where s.name = :name", Stock.class)
            .setParameter("name", itemname)
            .getSingleResult().getCode();
   }

   public String detail(String itemcode, String itemname) {

      String line ="";
      String result = "";
      ObjectMapper objectMapper = new ObjectMapper();
      JSONObject res = new JSONObject();

      try {
         String urlstr = "https://api.finance.naver.com/service/itemSummary.nhn?itemcode=" + itemcode;
         URL url = new URL(urlstr);

         BufferedReader br;
         br = new BufferedReader(new InputStreamReader(url.openStream()));
         while((line = br.readLine())!=null) {
            result = result.concat(line);
         }

         JSONParser parser = new JSONParser();
         JSONObject obj = (JSONObject) parser.parse(result);
         System.out.println(obj.toString());
         String amount = obj.get("amount").toString();//거래량
         String high = obj.get("high").toString(); //고점
         String rate = obj.get("rate").toString(); //등락비율
         String low = obj.get("low").toString(); //저점
         String now = obj.get("now").toString(); //현재가
         String diff = obj.get("diff").toString();//등락폭

         res.put("itemcode", itemcode);
         res.put("itemname", itemname);
         res.put("now", now);
         res.put("diff", diff);
         res.put("high", high);
         res.put("low", low);
         res.put("rate", rate);
         res.put("amount", amount);

         br.close();
      }catch (Exception e) {

      }

      return res.toJSONString();

   }

   public List<Stock> list() {

      return stockRepository.findAll();
   }
}