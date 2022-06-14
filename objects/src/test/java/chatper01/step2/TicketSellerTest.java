package chatper01.step2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("리팩토링된 TicketSeller 테스트")
class TicketSellerTest {

    private TicketOffice ticketOffice;
    private Audience audience;
    private Ticket ticket;
    private Long ticketOfficeAmount;
    private TicketSeller ticketSeller;

    @BeforeEach
    void init() {
        ticketOfficeAmount = 5000L;
        ticket = new Ticket(500L);
        ticketOffice = new TicketOffice(ticketOfficeAmount, new ArrayList<>(Arrays.asList(ticket, ticket)));
        ticketSeller = new TicketSeller(ticketOffice);
    }

    @Test
    @DisplayName("판매자가 티켓을 초대권이 있는 관객에게 판매하고, 금액을 office에 더한다.")
    void sellToInvitationAudience() {
        // given
        long amount = 500L;
        audience = new Audience(new Bag(amount, new Invitation()));
        // when
        ticketSeller.sellTo(audience);
        // then
        assertThat(ticketOffice.getAmount()).isEqualTo(ticketOfficeAmount);
        assertThat(audience.getBag().getAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("판매자가 티켓을 초대권이 없는 관객에게 판매하고, 금액을 office에 더한다.")
    void sellToNoInvitationAudience() {
        // given
        long amount = 500L;
        audience = new Audience(new Bag(amount));
        // when
        ticketSeller.sellTo(audience);
        // then
        assertThat(ticketOffice.getAmount()).isEqualTo(ticketOfficeAmount + ticket.getFee());
        assertThat(audience.getBag().getAmount()).isEqualTo(amount - ticket.getFee());
    }
}
