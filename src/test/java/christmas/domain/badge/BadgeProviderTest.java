package christmas.domain.badge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BadgeProviderTest {
    @DisplayName("20000원 이상이면 산타를 부여한다.")
    @Test
    void when_20000_Then_Provide_Santa() {
        //given
        int amount = 20_000;
        BadgeProvider badgeProvider = new BadgeProvider();

        //when
        Badge badge = badgeProvider.provide(amount);

        //then
        assertThat(badge).isEqualTo(Badge.SANTA);
    }

    @DisplayName("10000원 이상이면 트리를 부여한다.")
    @Test
    void when_10000_Then_Provide_Tree() {
        //given
        int amount = 10_000;
        BadgeProvider badgeProvider = new BadgeProvider();

        //when
        Badge badge = badgeProvider.provide(amount);

        //then
        assertThat(badge).isEqualTo(Badge.TREE);
    }

    @DisplayName("5000원 이상이면 트리를 부여한다.")
    @Test
    void when_5000_Then_Provide_Star() {
        //given
        int amount = 5_000;
        BadgeProvider badgeProvider = new BadgeProvider();

        //when
        Badge badge = badgeProvider.provide(amount);

        //then
        assertThat(badge).isEqualTo(Badge.STAR);
    }

    @DisplayName("5000원 미만이면 아무것도 부여하지 않는다.")
    @Test
    void when_2000_Then_Provide_None() {
        //given
        int amount = 2_000;
        BadgeProvider badgeProvider = new BadgeProvider();

        //when
        Badge badge = badgeProvider.provide(amount);

        //then
        assertThat(badge).isEqualTo(Badge.NONE);
    }

}
