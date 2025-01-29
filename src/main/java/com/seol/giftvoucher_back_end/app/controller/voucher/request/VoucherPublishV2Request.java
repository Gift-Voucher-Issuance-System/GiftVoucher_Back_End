package com.seol.giftvoucher_back_end.app.controller.voucher.request;

import com.seol.giftvoucher_back_end.common.type.RequesterType;
import com.seol.giftvoucher_back_end.common.type.VoucherAmountType;

public record VoucherPublishV2Request(
        RequesterType requesterType,
        String requesterId,
        VoucherAmountType amountType
) {
}