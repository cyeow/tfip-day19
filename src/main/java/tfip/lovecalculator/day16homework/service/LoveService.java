package tfip.lovecalculator.day16homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import tfip.lovecalculator.day16homework.model.LoveCalculatorInput;
import tfip.lovecalculator.day16homework.model.LoveCalculatorResult;
import tfip.lovecalculator.day16homework.repository.LoveRepository;

@Service
public class LoveService {

    @Autowired
    LoveRepository repo;

    @Value("${api.lovecalculator.endpoint}")
    private String lcUrl;

    @Value("${api.lovecalculator.key}")
    private String lcKey;

    @Value("${api.lovecalculator.host}")
    private String lcHost;

    public LoveCalculatorResult calculateLove(LoveCalculatorInput input) {
        String url = UriComponentsBuilder
                .fromUriString(lcUrl)
                .queryParam("fname", input.getFname())
                .queryParam("sname", input.getSname())
                .toUriString();

        RequestEntity<Void> req = RequestEntity
                .get(url)
                .header("X-RapidAPI-Key", lcKey)
                .header("X-RapidAPI-Host", lcHost)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = template.exchange(req, String.class);

        LoveCalculatorResult result = LoveCalculatorResult.createFromJSON(resp.getBody());

        if(result == null) {
            return null;
        } 

        saveLove(result);
        return result;
    }

    public void saveLove(LoveCalculatorResult result) {
        repo.save(result);
    }
}
