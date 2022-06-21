package chatper01.step3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("리팩토링한 Bag 테스트")
class BagTest {

    private Bag bag;
    private Ticket ticket;
    private Long fee;
    private Long amount;

    @BeforeEach
    void init() {
        fee = 500L;
        amount = 500L;
    }

    @Test
    @DisplayName("초대장이 있는 경우 무료로 구매")
    void holdWithInvitation() {
        // given
        bag = new Bag(amount, new Invitation());
        ticket = new Ticket(fee);
        // when
        Long holdAmount = bag.hold(ticket);
        // then
        assertThat(bag.getAmount()).isEqualTo(amount);
        assertThat(holdAmount).isZero();
    }

    @Test
    @DisplayName("초대장이 없는 경우 티켓 금액을 지불하고 구매")
    void holdWithNoInvitation() {
        // given
        bag = new Bag(amount);
        ticket = new Ticket(fee);
        // when
        Long holdAmount = bag.hold(ticket);
        // then
        assertThat(bag.getAmount()).isEqualTo(amount-fee);
        assertThat(holdAmount).isEqualTo(ticket.getFee());
    }
}
