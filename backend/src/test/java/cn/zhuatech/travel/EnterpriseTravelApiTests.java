/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.travel;
import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;import org.springframework.http.MediaType;import org.springframework.test.web.servlet.MockMvc;import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc class EnterpriseTravelApiTests {@Autowired MockMvc mvc;
 @Test void advanceAndPolicyLimitsAreSettledPerItem() throws Exception {mvc.perform(post("/api/enterprise/travel/settle").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"tripNo":"TR-001","advanceAmount":1000,"items":[{"category":"酒店","amount":1200,"policyLimit":800,"receiptRequired":true,"receiptAvailable":true,"companyPaid":false,"businessRelated":true},{"category":"机票","amount":1500,"policyLimit":1800,"receiptRequired":true,"receiptAvailable":true,"companyPaid":true,"businessRelated":true}]}
 """)).andExpect(status().isOk()).andExpect(jsonPath("$.data.employeeEligible").value(800.0)).andExpect(jsonPath("$.data.employeeRecoverable").value(200.0)).andExpect(jsonPath("$.data.rejected").value(400.0)).andExpect(jsonPath("$.data.decision").value("REVIEW_REQUIRED"));}
 @Test void missingReceiptRejectsExpense() throws Exception {mvc.perform(post("/api/enterprise/travel/settle").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"tripNo":"TR-002","advanceAmount":0,"items":[{"category":"餐饮","amount":300,"policyLimit":500,"receiptRequired":true,"receiptAvailable":false,"companyPaid":false,"businessRelated":true}]}
 """)).andExpect(status().isOk()).andExpect(jsonPath("$.data.employeeEligible").value(0.0));}
}
