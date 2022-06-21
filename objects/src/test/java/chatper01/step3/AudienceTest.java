package chatper01.step3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("리팩토링한 Audience 테스트")
class AudienceTest {

    private Audience audience;
    private Ticket ticket;
    private Long bagAmount;
    private Long fee;

    @BeforeEach
    void init() {
        fee = 500L;
        bagAmount = 1000L;
        ticket = new Ticket(fee);
    }

    @Test
    @DisplayName("초대장이 있는 경우 금액을 지불하지 않고 구매한다.")
    void buyWithInvitation() {
        // given
        audience = new Audience(new Bag(bagAmount, new Invitation()));
        // when
        Long buyAmount = audience.buy(ticket);
        // then
        assertThat(buyAmount).isZero();
        assertThat(audience.getBag().getAmount()).isEqualTo(bagAmount);
    }

    @Test
    @DisplayName("초대장이 없는 경우 금액을 지불하고 구매한다.")
    void buyWithNoInvitation() {
        // given
        audience = new Audience(new Bag(bagAmount));
        // when
        Long buyAmount = audience.buy(ticket);
        // then
        assertThat(buyAmount).isEqualTo(fee);
        assertThat(audience.getBag().getAmount()).isEqualTo(bagAmount-fee);
    }
}
