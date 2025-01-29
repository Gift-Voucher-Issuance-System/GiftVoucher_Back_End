package com.seol.giftvoucher_back_end.common.dto;

import com.seol.giftvoucher_back_end.common.type.RequesterType;

public record RequestContext(
        RequesterType requesterType,
        String requesterId
) {
}