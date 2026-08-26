/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.travel.service;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.math.*;import java.util.*;
@Service public class EnterpriseTravelService {
 public Settlement settle(@Valid SettlementRequest req){
  BigDecimal eligible=BigDecimal.ZERO,companyPaid=BigDecimal.ZERO,rejected=BigDecimal.ZERO;List<ItemResult> items=new ArrayList<>();List<String> violations=new ArrayList<>();
  for(var item:req.items()){
   BigDecimal approved=item.amount().min(item.policyLimit());
   List<String> reasons=new ArrayList<>();
   if(item.amount().compareTo(item.policyLimit())>0)reasons.add("超过分类限额");
   if(item.receiptRequired()&&!item.receiptAvailable()){approved=BigDecimal.ZERO;reasons.add("缺少合规票据");}
   if(!item.businessRelated()){approved=BigDecimal.ZERO;reasons.add("非公务支出");}
   if(item.companyPaid())companyPaid=companyPaid.add(approved);else eligible=eligible.add(approved);
   rejected=rejected.add(item.amount().subtract(approved));reasons.forEach(reason->violations.add(item.category()+": "+reason));
   items.add(new ItemResult(item.category(),money(item.amount()),money(approved),reasons));
  }
  BigDecimal employeePayable=eligible.subtract(req.advanceAmount()).max(BigDecimal.ZERO);
  BigDecimal employeeRecoverable=req.advanceAmount().subtract(eligible).max(BigDecimal.ZERO);
  return new Settlement(req.tripNo(),money(eligible),money(companyPaid),money(rejected),money(employeePayable),money(employeeRecoverable),items,violations,
    violations.isEmpty()?"READY_FOR_SETTLEMENT":"REVIEW_REQUIRED");
 }
 private BigDecimal money(BigDecimal v){return v.setScale(2,RoundingMode.HALF_UP);}
 public record SettlementRequest(@NotBlank String tripNo,@NotNull @DecimalMin("0") BigDecimal advanceAmount,@NotEmpty List<@Valid ExpenseItem> items){}
 public record ExpenseItem(@NotBlank String category,@NotNull @DecimalMin("0") BigDecimal amount,@NotNull @DecimalMin("0") BigDecimal policyLimit,
   boolean receiptRequired,boolean receiptAvailable,boolean companyPaid,boolean businessRelated){}
 public record ItemResult(String category,BigDecimal claimed,BigDecimal approved,List<String> reasons){}
 public record Settlement(String tripNo,BigDecimal employeeEligible,BigDecimal companyPaid,BigDecimal rejected,BigDecimal employeePayable,
   BigDecimal employeeRecoverable,List<ItemResult> items,List<String> violations,String decision){}
}
