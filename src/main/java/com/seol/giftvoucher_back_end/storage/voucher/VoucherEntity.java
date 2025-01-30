package com.seol.giftvoucher_back_end.storage.voucher;

import com.seol.giftvoucher_back_end.common.type.VoucherAmountType;
import com.seol.giftvoucher_back_end.common.type.VoucherStatusType;
import com.seol.giftvoucher_back_end.storage.BaseEntity;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Table(name = "voucher")
@Entity
public class VoucherEntity extends BaseEntity {
    private String code;
    @Enumerated(EnumType.STRING)
    private VoucherStatusType status;
    private LocalDate validFrom;
    private LocalDate validTo;
    @Enumerated(EnumType.STRING)
    private VoucherAmountType amount;

    //    @OneToMany(cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "voucher_id")
    private List<VoucherHistoryEntity> histories = new ArrayList<>();

    public VoucherEntity() {
    }

    public VoucherEntity(String code, VoucherStatusType status, LocalDate validFrom, LocalDate validTo, VoucherAmountType amount, VoucherHistoryEntity voucherHistoryEntity) {
        this.code = code;
        this.status = status;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.amount = amount;

        this.histories.add(voucherHistoryEntity);
    }

    public String code() {
        return code;
    }

    public VoucherStatusType status() {
        return status;
    }

    public LocalDate validFrom() {
        return validFrom;
    }

    public LocalDate validTo() {
        return validTo;
    }

    public VoucherAmountType amount() {
        return amount;
    }

    public List<VoucherHistoryEntity> histories() {
        return histories;
    }

    public void disable() {
        log.info("비활성화 전 상태: {}", this.status);

        if (!this.status.equals(VoucherStatusType.PUBLISH)) {
            throw new IllegalStateException("사용 불가 처리할 수 없는 상태의 상품권 입니다.");
        }

        this.status = VoucherStatusType.DISABLE;

        log.info("비활성화 후 상태: {}", this.status);
    }

    public void use() {
        if (!this.status.equals(VoucherStatusType.PUBLISH)) {
            throw new IllegalStateException("사용할 수 없는 상태의 상품권 입니다.");
        }

        this.status = VoucherStatusType.USE;
    }
}