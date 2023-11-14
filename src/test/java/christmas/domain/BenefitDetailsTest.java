package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import christmas.Menu;
import christmas.Order;
import christmas.Orders;
import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.DDayDiscount;
import christmas.domain.benefit.Giveaway;
import christmas.domain.benefit.NoBenefit;
import christmas.domain.benefit.SpecialDiscount;
import christmas.domain.benefit.WeekDayDiscount;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitDetailsTest {
    @DisplayName("details 메소드가 알맞은 값을 반환한다.")
    @Test
    void details_Test_Success() {
        //given
        LocalDate localDate = LocalDate.of(2023, 12, 1);
        Orders orders = new Orders(List.of(new Order(Menu.CHOCOLATE_CAKE, 2)));

        DDayDiscount dDayDiscount = new DDayDiscount(localDate);
        WeekDayDiscount weekDayDiscount = new WeekDayDiscount(orders);
        SpecialDiscount specialDiscount = new SpecialDiscount();
        Giveaway giveaway = new Giveaway();

        AllBenefits allBenefits = new AllBenefitsBuilder().dDayDiscount(dDayDiscount)
                .weekDiscount(weekDayDiscount)
                .specialDiscount(specialDiscount)
                .giveaway(giveaway)
                .build();

        //when
        Map<String, Integer> details = allBenefits.getDetails();

        //then
        assertThat(details).hasSize(4)
                .contains(entry("크리스마스 디데이 할인", 1000),
                        entry("평일 할인", 4046),
                        entry("특별 할인", 1000),
                        entry("증정 이벤트", 25000));
    }

    @DisplayName("적용되지 않은 혜택은 반환되지 않는다.")
    @Test
    void when_Some_Benefits_Are_Not_Applied_Then_Not_Return_Them() {
        //given
        LocalDate localDate = LocalDate.of(2023, 12, 1);
        Orders orders = new Orders(List.of(new Order(Menu.CHOCOLATE_CAKE, 2)));

        DDayDiscount dDayDiscount = new DDayDiscount(localDate);
        WeekDayDiscount weekDayDiscount = new WeekDayDiscount(orders);
        Benefit specialDiscount = new NoBenefit();
        Benefit giveaway = new NoBenefit();

        AllBenefits allBenefits = new AllBenefitsBuilder().dDayDiscount(dDayDiscount)
                .weekDiscount(weekDayDiscount)
                .specialDiscount(specialDiscount)
                .giveaway(giveaway)
                .build();

        //when
        Map<String, Integer> details = allBenefits.getDetails();

        //then
        assertThat(details).hasSize(2)
                .contains(entry("크리스마스 디데이 할인", 1000),
                        entry("평일 할인", 4046));
    }
}