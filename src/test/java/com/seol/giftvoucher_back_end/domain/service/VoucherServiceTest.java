package com.seol.giftvoucher_back_end.domain.service;

import com.seol.giftvoucher_back_end.common.type.VoucherAmountType;
import com.seol.giftvoucher_back_end.common.type.VoucherStatusType;
import com.seol.giftvoucher_back_end.storage.voucher.VoucherEntity;
import com.seol.giftvoucher_back_end.storage.voucher.VoucherRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VoucherServiceTest {
    @Autowired
    private VoucherService voucherService;

    @Autowired
    private VoucherRepository voucherRepository;

    @DisplayName("발행된 상품권은 code로 조회 가능해야 함")
    @Test
    public void givenCode_whenRequest_thenReturnSuccess(){
        //Given
        final LocalDate validFrom = LocalDate.now();
        final LocalDate validTo = LocalDate.now().plusDays(30);

        final VoucherAmountType amount = VoucherAmountType.KRW_30000;

        final String code = voucherService.publish(validFrom, validTo, amount);

        //When
        final VoucherEntity voucherEntity = voucherRepository.findByCode(code).get();

        //Then
        assertThat(voucherEntity.code()).isEqualTo(code);
        assertThat(voucherEntity.status()).isEqualTo(VoucherStatusType.PUBLISH);
        assertThat(voucherEntity.validFrom()).isEqualTo(validFrom);
        assertThat(voucherEntity.validTo()).isEqualTo(validTo);
        assertThat(voucherEntity.amount()).isEqualTo(amount);
    }

    @DisplayName("발행된 상품권은 사용 불가 처리 할 수 있음")
    @Test
    void givenIssuedVoucher_whenDisable_thenSuccess(){
        //Given
        final LocalDate validFrom = LocalDate.now();
        final LocalDate validTo = LocalDate.now().plusDays(30);
        final VoucherAmountType amount = VoucherAmountType.KRW_30000;

        final String code = voucherService.publish(validFrom, validTo, amount);

        //When
        voucherService.disable(code);
        final VoucherEntity voucherEntity = voucherRepository.findByCode(code).get();

        //Then
        assertThat(voucherEntity.code()).isEqualTo(code);
        assertThat(voucherEntity.status()).isEqualTo(VoucherStatusType.DISABLE);
        assertThat(voucherEntity.validFrom()).isEqualTo(validFrom);
        assertThat(voucherEntity.validTo()).isEqualTo(validTo);
        assertThat(voucherEntity.amount()).isEqualTo(amount);
        assertThat(voucherEntity.updateAt()).isNotEqualTo(voucherEntity.createAt());

        System.out.println("voucherEntity.createAt() =" + voucherEntity.createAt());
        System.out.println("voucherEntity.updateAt() =" + voucherEntity.updateAt());
    }

    @DisplayName("발행된 상품권은 사용 가능 처리 할 수 있음")
    @Test
    void givenIssuedVoucher_whenEnable_thenSuccess(){
        //Given
        final LocalDate validFrom = LocalDate.now();
        final LocalDate validTo = LocalDate.now().plusDays(30);
        final VoucherAmountType amount = VoucherAmountType.KRW_30000;

        final String code = voucherService.publish(validFrom, validTo, amount);

        //When
        voucherService.use(code);
        final VoucherEntity voucherEntity = voucherRepository.findByCode(code).get();

        //Then
        assertThat(voucherEntity.code()).isEqualTo(code);
        assertThat(voucherEntity.status()).isEqualTo(VoucherStatusType.USE);
        assertThat(voucherEntity.validFrom()).isEqualTo(validFrom);
        assertThat(voucherEntity.validTo()).isEqualTo(validTo);
        assertThat(voucherEntity.amount()).isEqualTo(amount);
        assertThat(voucherEntity.updateAt()).isNotEqualTo(voucherEntity.createAt());

        System.out.println("voucherEntity.createAt() =" + voucherEntity.createAt());
        System.out.println("voucherEntity.updateAt() =" + voucherEntity.updateAt());
    }
}