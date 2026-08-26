/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.travel.controller;
import cn.zhuatech.travel.common.ApiResponse;import cn.zhuatech.travel.service.EnterpriseTravelService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/enterprise/travel") public class EnterpriseTravelController {private final EnterpriseTravelService service;public EnterpriseTravelController(EnterpriseTravelService service){this.service=service;}@PostMapping("/settle") ApiResponse<EnterpriseTravelService.Settlement> settle(@Valid @RequestBody EnterpriseTravelService.SettlementRequest request){return ApiResponse.ok(service.settle(request));}}
