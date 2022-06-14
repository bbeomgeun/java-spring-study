package chatper01.step2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("리팩토링한 Theater 테스트")
class TheaterTest {

    private TicketSeller ticketSeller;
    private TicketOffice ticketOffice;
    private Theater theater;
    private Long ticketOfficeAmount;
    private Audience audience;
    private Long bagAmount;

    @BeforeEach
    void init() {
        bagAmount = 500L;
        ticketOfficeAmount = 5000L;
        ticketOffice = new TicketOffice(ticketOfficeAmount, new ArrayList<>(Arrays.asList(new Ticket(500L), new Ticket(500L))));
        ticketSeller = new TicketSeller(ticketOffice);
        theater = new Theater(ticketSeller);
    }

    @Test
    @DisplayName("초대장이 있는 고객이 입장한다.")
    void enterWithInvitation() {
        // given
        audience = new Audience(new Bag(bagAmount, new Invitation()));

        // when
        theater.enter(audience);

        // then
        assertThat(ticketOffice.getAmount()).isEqualTo(ticketOfficeAmount);
        assertThat(audience.getBag().getAmount()).isEqualTo(bagAmount);
    }

    @Test
    @DisplayName("초대장이 없는 고객이 티켓을 구입 후 입장한다.")
    void enterWithNoInvitation() {
        // given
        audience = new Audience(new Bag(bagAmount));
        Long fee = ticketOffice.getTicket().getFee();
        // when
        theater.enter(audience);

        // then
        assertThat(ticketOffice.getAmount()).isEqualTo(ticketOfficeAmount + fee);
        assertThat(audience.getBag().getAmount()).isEqualTo(bagAmount - fee);
    }
}
