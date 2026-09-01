/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.travel.controller;

import cn.zhuatech.travel.common.ApiResponse;
import cn.zhuatech.travel.service.TravelBookingReleaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enterprise/travel")
public class TravelBookingReleaseController {
    private final TravelBookingReleaseService service;
    public TravelBookingReleaseController(TravelBookingReleaseService service) { this.service = service; }

    @PostMapping("/booking-release")
    public ApiResponse<?> assess(@RequestBody TravelBookingReleaseService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
