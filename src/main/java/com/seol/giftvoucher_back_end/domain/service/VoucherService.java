package com.seol.giftvoucher_back_end.domain.service;

import com.seol.giftvoucher_back_end.common.dto.RequestContext;
import com.seol.giftvoucher_back_end.common.type.VoucherAmountType;
import com.seol.giftvoucher_back_end.common.type.VoucherStatusType;
import com.seol.giftvoucher_back_end.storage.voucher.VoucherEntity;
import com.seol.giftvoucher_back_end.storage.voucher.VoucherHistoryEntity;
import com.seol.giftvoucher_back_end.storage.voucher.VoucherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
public class VoucherService {
    private final VoucherRepository voucherRepository;

    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    // 상품권 발행 v1
    @Transactional
    public String publish(final LocalDate validFrom, final LocalDate validTo, final VoucherAmountType amount) {
        final String code = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
        final VoucherEntity voucherEntity = new VoucherEntity(code, VoucherStatusType.PUBLISH, validFrom, validTo, amount, null);

        return voucherRepository.save(voucherEntity).code();
    }

    // 상품권 사용 불가 처리 v1
    @Transactional
    public void disable(String code) {
        final VoucherEntity voucherEntity = voucherRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품권입니다."));
    }

    // 상품권 사용 v1
    @Transactional
    public void use(String code) {
        final VoucherEntity voucherEntity = voucherRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품권입니다."));

        voucherEntity.use();
    }

    // 상품권 발행 v2
    @Transactional
    public String publishV2(final RequestContext requestContext, final LocalDate validFrom, final LocalDate validTo, final VoucherAmountType amount) {
        final String code = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
        final String orderId = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");

        final VoucherHistoryEntity voucherHistoryEntity = new VoucherHistoryEntity(orderId, requestContext.requesterType(), requestContext.requesterId(), VoucherStatusType.PUBLISH, "테스트 발행");
        final VoucherEntity voucherEntity = new VoucherEntity(code, VoucherStatusType.PUBLISH, validFrom, validTo, amount, voucherHistoryEntity);

        return voucherRepository.save(voucherEntity).code();
    }

    // 상품권 사용 불가 처리 v2
    @Transactional
    public void disableV2(final RequestContext requestContext, final String code) {
        final VoucherEntity voucherEntity = voucherRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품권입니다."));

        log.info("디버깅 - 비활성화 전 상태: {}", voucherEntity.status());

        voucherEntity.disable();  // 상태 변경

        log.info("디버깅 - 비활성화 후 상태: {}", voucherEntity.status());

        voucherRepository.save(voucherEntity);  // DB 저장
        voucherRepository.flush();  // 🚀 강제 반영

        log.info("디버깅 - save() 실행 및 flush 완료");
    }

    // 상품권 사용 v2
    @Transactional
    public void useV2(final RequestContext requestContext, final String code) {
        final VoucherEntity voucherEntity = voucherRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품권입니다."));

        voucherEntity.use();
    }
}