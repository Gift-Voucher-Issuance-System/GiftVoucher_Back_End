package com.seol.giftvoucher_back_end.app.controller.voucher.request;

import com.seol.giftvoucher_back_end.common.type.RequesterType;
import com.seol.giftvoucher_back_end.common.type.VoucherAmountType;

public record VoucherPublishV3Request(
        RequesterType requesterType,
        String requesterId,
        String contractCode,
        VoucherAmountType amountType
) {
}