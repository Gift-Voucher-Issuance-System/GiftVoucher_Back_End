package com.seol.giftvoucher_back_end.app.controller.voucher.request;

import com.seol.giftvoucher_back_end.common.type.RequesterType;

public record VoucherUseV2Request(
        RequesterType requesterType,
        String requesterId,
        String code
) {
}
