/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.travel.domain;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class DomainCatalog {
    private final Map<String,WorkflowAction> actions=new LinkedHashMap<>();
    public DomainCatalog(){
        actions.put("SUBMIT", new WorkflowAction("SUBMIT", "提交申请", List.of("草稿"), "待审批"));
actions.put("APPROVE", new WorkflowAction("APPROVE", "审批通过", List.of("待审批"), "已批准"));
actions.put("BOOK", new WorkflowAction("BOOK", "完成预订", List.of("已批准"), "已预订"));
actions.put("START", new WorkflowAction("START", "开始行程", List.of("已预订"), "进行中"));
actions.put("SETTLE", new WorkflowAction("SETTLE", "完成结算", List.of("进行中"), "已完成"));
    }
    public String systemName(){return "知华科技企业商旅管理系统";}
    public String scene(){return "出差申请、预算审批、行程预订、差旅费用和报销结算管理";}
    public String initialStatus(){return "草稿";}
    public String partyLabel(){return "出差人/部门";} public String amountLabel(){return "差旅预算";}
    public String quantityLabel(){return "出差天数";} public String dueLabel(){return "出发日期";}
    public List<ModuleDefinition> modules(){return List.of(
        new ModuleDefinition("REQUEST","出差申请","登记目的地、事由、人员、时间与预算"),
    new ModuleDefinition("ITINERARY","行程与预订","维护交通、酒店和行程变更记录"),
    new ModuleDefinition("EXPENSE","差旅费用","归集票据、费用明细与差标校验"),
    new ModuleDefinition("SETTLEMENT","报销结算","完成报销审批、借款冲销和财务付款")
    );}
    public Map<String,WorkflowAction> actions(){return Collections.unmodifiableMap(actions);}
    public record ModuleDefinition(String code,String name,String description){}
    public record WorkflowAction(String code,String label,List<String> from,String to){}
}
