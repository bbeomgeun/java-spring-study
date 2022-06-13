package chatper01.step1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class TheaterTest {

    private Long amount;
    private Audience audience;
    private Bag bag;
    private TicketSeller ticketSeller;
    private TicketOffice ticketOffice;
    private Theater theater;

    @BeforeEach
    void init() {
        amount = 500L;
        ticketOffice = new TicketOffice(amount, new ArrayList<>(Arrays.asList(new Ticket(500L), new Ticket(500L))));
        ticketSeller = new TicketSeller(ticketOffice);
        theater = new Theater(ticketSeller);
    }

    @Test
    @DisplayName("손님이 초대장이 있는 경우 초대장과 티켓을 교환하고, 돈은 줄어들지 않는다.")
    void enterWithInvitation() {

        // given
        Long beforeAmount = ticketOffice.getAmount();
        bag = new Bag(this.amount, new Invitation());
        audience = new Audience(bag);

        // when
        theater.enter(audience);

        // then
        assertThat(bag.hasInvitation()).isTrue();
        assertThat(audience.getBag().hasTicket()).isTrue();
        assertThat(bag.getAmount()).isEqualTo(this.amount);
        assertThat(ticketOffice.getAmount()).isEqualTo(beforeAmount);
    }

    @Test
    @DisplayName("손님이 초대장이 없는 경우 티켓 금액만큼 돈을 지불하고 티켓을 구입한다.")
    void enterWithNoInvitation() {

        // given
        Long ticketFee = ticketOffice.getTicket().getFee();
        Long beforeAmount = ticketOffice.getAmount();
        bag = new Bag(amount);
        audience = new Audience(bag);

        // when
        theater.enter(audience);

        // then
        assertThat(bag.hasInvitation()).isFalse();
        assertThat(bag.getAmount()).isEqualTo(amount - ticketFee);
        assertThat(bag.hasTicket()).isTrue();
        assertThat(ticketOffice.getAmount()).isEqualTo(beforeAmount + ticketFee);
    }
}
