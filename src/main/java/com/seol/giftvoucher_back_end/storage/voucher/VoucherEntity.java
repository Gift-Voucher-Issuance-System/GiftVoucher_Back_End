package com.seol.giftvoucher_back_end.storage.voucher;

import com.seol.giftvoucher_back_end.common.type.VoucherStatusType;
import com.seol.giftvoucher_back_end.storage.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Table(name = "voucher")
@Entity
public class VoucherEntity extends BaseEntity {
    private String code;
    private VoucherStatusType status;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Long amount;

    public VoucherEntity(){}

    public VoucherEntity(String code, VoucherStatusType status, LocalDate validFrom, LocalDate validTo, Long amount){
        this.code = code;
        this.status = status;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.amount = amount;
    }


    public String code(){
        return code;
    }

    public VoucherStatusType status(){
        return status;
    }

    public LocalDate validFrom(){
        return validFrom;
    }

    public LocalDate validTo(){
        return validTo;
    }

    public Long amount(){
        return amount;
    }

    public void disalbe() {
        if(!this.status.equals(VoucherStatusType.PUBLISH)){
            throw new IllegalStateException("사용 불가 처리할 수 없는 상태의 상품권입니다.");
        }

        this.status = VoucherStatusType.DISABLE;
    }

    public void use() {
        if(!this.status.equals(VoucherStatusType.PUBLISH)){
            throw new IllegalStateException("사용할 수 없는 상태의 상품권입니다.");
        }

        this.status = VoucherStatusType.USE;
    }
}
