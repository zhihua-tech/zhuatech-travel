/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.travel.service;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.*;
import java.util.*;
@Service
public class DomainInsightService {
    public Map<String,Object> analyze(InsightRequest req){
        Map<String,Object> result=new LinkedHashMap<>();
        List<String> violations=new ArrayList<>();
if(req.estimatedCost().compareTo(req.budget())>0)violations.add("预计费用超过预算");
if(!req.hotelWithinPolicy())violations.add("住宿超过差标");if(!req.transportWithinPolicy())violations.add("交通方式不符合差标");if(!req.receiptAvailable())violations.add("票据条件不完整");
result.put("variance",req.budget().subtract(req.estimatedCost()));result.put("violations",violations);result.put("decision",violations.size()>=2?"BLOCK":violations.isEmpty()?"APPROVE":"REVIEW");
        return result;
    }
    private BigDecimal rate(long numerator,long denominator){return denominator==0?BigDecimal.ZERO:BigDecimal.valueOf(numerator).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(denominator),2,RoundingMode.HALF_UP);}
    public record InsightRequest(@DecimalMin("0.0") BigDecimal budget, @DecimalMin("0.0") BigDecimal estimatedCost, boolean hotelWithinPolicy, boolean transportWithinPolicy, boolean receiptAvailable){}
}
