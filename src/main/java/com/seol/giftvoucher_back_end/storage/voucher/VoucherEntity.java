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
    private LocalDate validto;
    private Long amount;

    public VoucherEntity(){}

    public VoucherEntity(String code, VoucherStatusType status, LocalDate validFrom, LocalDate validto, Long amount){
        this.code = code;
        this.status = status;
        this.validFrom = validFrom;
        this.validto = validto;
        this.amount = amount;
    }

    /*
    public String code(){
        return code;
    }

    public VoucherStatusType status(){
        return status;
    }

    public LocalDate validFrom(){
        return validFrom;
    }

    public LocalDate validto(){
        return validto;
    }

    public Long amount(){
        return amount;
    }*/
}
