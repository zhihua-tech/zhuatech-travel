/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.travel.config;
import cn.zhuatech.travel.model.*;
import cn.zhuatech.travel.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Configuration public class DataInitializer {
    @Bean CommandLineRunner seed(BusinessRecordRepository records,SystemSettingRepository settings){return args->{
        if(records.count()>0)return;
            settings.save(new SystemSetting("approvalMode","部门主管+预算负责人"));
    settings.save(new SystemSetting("bookingPolicy","优先协议供应商"));
    settings.save(new SystemSetting("currency","CNY"));
    settings.save(new SystemSetting("receiptRequired","是"));
            records.save(new BusinessRecord("TRV-20260826-001","REQUEST","苏州客户项目启动出差","实施交付部","项目经理","待审批",new BigDecimal("8600"),4,LocalDate.now().plusDays(3),"正常","两名实施顾问参与"));
    records.save(new BusinessRecord("TRV-20260826-002","ITINERARY","深圳合作伙伴会议行程","渠道部","行政专员","已预订",new BigDecimal("12800"),3,LocalDate.now().plusDays(8),"正常","机票酒店均符合差标"));
    records.save(new BusinessRecord("TRV-20260826-003","EXPENSE","北京售前支持费用归集","解决方案部","售前经理","进行中",new BigDecimal("5600"),2,LocalDate.now().plusDays(-1),"关注","一张住宿票据待补充"));
    records.save(new BusinessRecord("TRV-20260826-004","SETTLEMENT","杭州交付项目差旅结算","实施交付部","财务会计","已完成",new BigDecimal("9300"),5,LocalDate.now().plusDays(-6),"正常","借款冲销和付款已完成"));
    };}
}
