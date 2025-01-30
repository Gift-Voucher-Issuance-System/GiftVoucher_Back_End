package com.seol.giftvoucher_back_end.storage.voucher;

import com.seol.giftvoucher_back_end.common.type.VoucherAmountType;
import com.seol.giftvoucher_back_end.common.type.VoucherStatusType;
import com.seol.giftvoucher_back_end.storage.BaseEntity;
import jakarta.persistence.*;
import org.springframework.lang.Contract;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "contract")
@Entity
public class ContractEntity extends BaseEntity {
    private String code; //계약의 고유 코드
    private LocalDate validFrom; // 계약의 유효 기간 시작일
    private LocalDate validTo; // 계약의 유효 기간 종료일
    private Integer voucherValidPeriodDayCount; //상품권 유효일자

    public ContractEntity() {
    }

    public ContractEntity(String code, LocalDate validFrom, LocalDate validTo, Integer voucherValidPeriodDayCount){
        this.code = code;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.voucherValidPeriodDayCount = voucherValidPeriodDayCount;
    }

    public String code() {
        return code;
    }

    public LocalDate validFrom() {
        return validFrom;
    }

    public Integer voucherValidPeriodDayCount() {
        return voucherValidPeriodDayCount;
    }

    public LocalDate validTo() {
        return validTo;
    }
}