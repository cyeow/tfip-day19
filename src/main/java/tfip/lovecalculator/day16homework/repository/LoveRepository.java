package tfip.lovecalculator.day16homework.repository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import tfip.lovecalculator.day16homework.model.LoveCalculatorResult;

@Repository
public class LoveRepository {
    
    @Autowired
    RedisTemplate<String, String> template;

    // returns the saved key for the specific search
    // null if save failed
    public String save(LoveCalculatorResult result) {
        // key is epochsecond of the search
        String key = LocalDateTime.now().toString() + "";
        template.opsForValue().set(key, result.toJSON().toString());

        return template.opsForValue().get(key);
    }
}
