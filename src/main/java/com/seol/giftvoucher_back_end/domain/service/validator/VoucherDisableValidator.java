package com.seol.giftvoucher_back_end.domain.service.validator;

import com.seol.giftvoucher_back_end.common.dto.RequestContext;
import com.seol.giftvoucher_back_end.storage.voucher.ContractEntity;
import com.seol.giftvoucher_back_end.storage.voucher.VoucherEntity;
import org.springframework.stereotype.Component;

@Component
public class VoucherDisableValidator {

    public void validate(VoucherEntity voucherEntity, RequestContext requestContext) {
        상품권_사용_불가_처리_권한이_있는지_확인(voucherEntity, requestContext);
    }

    private static void 상품권_사용_불가_처리_권한이_있는지_확인(VoucherEntity voucherEntity, RequestContext requestContext) {
        if (voucherEntity.publishHistory().requesterType() != requestContext.requesterType()
                || !voucherEntity.publishHistory().requesterId().equals(requestContext.requesterId())) {
            throw new IllegalArgumentException("사용 불가 처리 권한이 없는 상품권 입니다.");
        }
    }
}