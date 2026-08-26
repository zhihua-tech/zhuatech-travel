/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.travel.controller;
import cn.zhuatech.travel.common.ApiResponse;
import cn.zhuatech.travel.service.DomainInsightService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/insights")
public class DomainInsightController {
    private final DomainInsightService service;public DomainInsightController(DomainInsightService service){this.service=service;}
    @PostMapping("/travel") ApiResponse<Map<String,Object>> analyze(@Valid @RequestBody DomainInsightService.InsightRequest request){return ApiResponse.ok(service.analyze(request));}
}
