package com.seol.giftvoucher_back_end.domain.service;

import com.seol.giftvoucher_back_end.common.type.VoucherStatusType;
import com.seol.giftvoucher_back_end.storage.voucher.VoucherEntity;
import com.seol.giftvoucher_back_end.storage.voucher.VoucherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class VoucherService {
    private final VoucherRepository voucherRepository;

    public VoucherService(VoucherRepository voucherRepository){
        this.voucherRepository = voucherRepository;
    }

    // 상품권 발행
    @Transactional
    public String publish(final LocalDate validFrom, final LocalDate validTo, final Long amount){
        final String code = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
        final VoucherEntity voucherEntity = new VoucherEntity(code, VoucherStatusType.PUBLISH, validFrom, validTo, amount);

        return voucherRepository.save(voucherEntity).code();
    }

    // 상품권 취소

    // 상품권 사용
}
