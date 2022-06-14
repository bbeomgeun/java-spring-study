package chatper01.step2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("리팩토링 된 Audience 테스트")
class AudienceTest {

    private Bag bag;
    private Long amount;
    private Audience audience;
    private Ticket ticket;
    private Long fee;

    @BeforeEach
    void init() {
        amount = 500L;
        fee = 500L;
        ticket = new Ticket(fee);
    }

    @Test
    @DisplayName("초대장이 있는 고객은 티켓을 구입하고, 티켓값은 지불하지 않는다.")
    void buyWithInvitation() {
        // given
        bag = new Bag(amount, new Invitation());
        audience = new Audience(bag);

        // when
        Long returnFee = audience.buy(ticket);

        // then
        assertThat(audience.getBag().hasInvitation()).isTrue();
        assertThat(returnFee).isZero();
        assertThat(audience.getBag().hasTicket()).isTrue();
        assertThat(audience.getBag().getAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("초대장이 없는 고객은 티켓을 구입하고, 티켓값을 지불한다")
    void buyWithNoInvitation() {
        // given
        bag = new Bag(amount);
        audience = new Audience(bag);

        // when
        Long returnFee = audience.buy(ticket);

        // then
        assertThat(audience.getBag().hasInvitation()).isFalse();
        assertThat(audience.getBag().getAmount()).isEqualTo(amount - fee);
        assertThat(returnFee).isEqualTo(fee);
        assertThat(audience.getBag().hasTicket()).isTrue();
    }
}
