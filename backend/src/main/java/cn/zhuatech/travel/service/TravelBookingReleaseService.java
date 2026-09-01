/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.travel.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TravelBookingReleaseService {
    public Result assess(Request request) {
        var blockers = new ArrayList<String>();
        var actions = new ArrayList<String>();
        if (request.requestId() == null || request.requestId().isBlank()) blockers.add("差旅申请编号不能为空");
        if (!request.managerApproved()) blockers.add("出差申请未获得主管批准");
        if (!request.budgetAvailable()) blockers.add("差旅预算不足");
        if (!request.itineraryRiskReviewed()) blockers.add("目的地与行程风险未评估");
        if (request.visaRequired() && !request.visaValid()) blockers.add("必要签证无效或缺失");
        if (!request.auditReady()) blockers.add("差旅预订审计证据不完整");
        if (!request.policyCompliant()) actions.add("复核并处理差旅政策例外");
        if (!request.travelerProfileComplete()) actions.add("补齐旅客档案");
        if (!request.preferredSupplierUsed()) actions.add("记录非协议供应商原因");
        if (!request.advanceReconciled()) actions.add("清理未核销差旅预借款");
        if (!request.emergencyContactPresent()) actions.add("补充紧急联系人");
        var decision = !blockers.isEmpty() ? Decision.BLOCKED : actions.isEmpty() ? Decision.BOOK : Decision.REVIEW;
        return new Result(decision, List.copyOf(blockers), List.copyOf(actions));
    }

    public enum Decision { BOOK, REVIEW, BLOCKED }
    public record Request(String requestId, boolean policyCompliant, boolean managerApproved,
                          boolean budgetAvailable, boolean travelerProfileComplete,
                          boolean itineraryRiskReviewed, boolean visaRequired, boolean visaValid,
                          boolean preferredSupplierUsed, boolean advanceReconciled,
                          boolean emergencyContactPresent, boolean auditReady) {}
    public record Result(Decision decision, List<String> blockers, List<String> actions) {}
}
