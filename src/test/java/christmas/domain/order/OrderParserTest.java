package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.entry;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderParserTest {
    @DisplayName("메뉴 이름과 개수로 분리한다.")
    @Test
    void parse_Success() {
        //given
        OrderParser orderParser = new OrderParser();

        //when
        String input = "타파스-1,제로콜라-1";
        Orders orders = orderParser.convert(input);
        Map<String, Integer> orderedMenu = orders.orderedMenu();

        //then
        assertThat(orderedMenu).hasSize(2)
                .contains(entry("타파스", 1), entry("제로콜라", 1));
    }

    @DisplayName("하이픈으로 연결된 요소가 두 개 이상이면 예외를 반환한다.")
    @Test
    void throw_Exception_If_Connected_Over_Two_Elements_with_Hyphen() {
        //given
        OrderParser orderParser = new OrderParser();

        //when
        String input = "타파스-1-2,제로콜라-1-2";

        //then
        assertThatThrownBy(() -> orderParser.convert(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("개수를 숫자로 입력하지 않으면 예외를 반환한다.")
    @Test
    void throw_Exception_If_Not_Numeric() {
        //given
        OrderParser orderParser = new OrderParser();

        //when
        String input = "타파스-하나,제로콜라-둘";

        //then
        assertThatThrownBy(() -> orderParser.convert(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("하이픈으로 분리되지 않았다면 예외를 반환한다.")
    @Test
    void throw_Exception_If_Not_Split_With_Hyphen() {
        //given
        OrderParser orderParser = new OrderParser();

        //when
        String input = "타파스*하나,제로콜라*둘";

        //then
        assertThatThrownBy(() -> orderParser.convert(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("쉼표로 분리되지 않았다면 예외를 반환한다.")
    @Test
    void throw_Exception_If_Not_Split_With_Comma() {
        //given
        OrderParser orderParser = new OrderParser();

        //when
        String input = "타파스-하나 제로콜라-둘";

        //then
        assertThatThrownBy(() -> orderParser.convert(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("쉼표만 분리되었다면 예외를 반환한다.")
    @Test
    void throw_Exception_If_Split_With_Only_Comma() {
        //given
        OrderParser orderParser = new OrderParser();

        //when
        String input = "타파스,하나,제로콜라,둘";

        //then
        assertThatThrownBy(() -> orderParser.convert(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("없는 메뉴를 입력하면 예외를 반환한다.")
    @Test
    void throw_Exception_When_Invalid_Menu() {
        //given
        OrderParser orderParser = new OrderParser();

        //when
        String input = "라자냐-1,제로콜라-2";

        //then
        assertThatThrownBy(() -> orderParser.convert(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("총 메뉴 개수를 20개 초과로 입력하면 예외를 반환한다.")
    @Test
    void throw_Exception_When_Menu_Number_Over_20() {
        //given
        OrderParser orderParser = new OrderParser();

        //when
        String input = "타파스-10,제로콜라-11";

        //then
        assertThatThrownBy(() -> orderParser.convert(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴를 1개 미만으로 입력하면 예외를 반환한다.")
    @Test
    void throw_Exception_When_Menu_Number_Under_1() {
        //given
        OrderParser orderParser = new OrderParser();

        //when
        String input = "타파스-0";

        //then
        assertThatThrownBy(() -> orderParser.convert(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}