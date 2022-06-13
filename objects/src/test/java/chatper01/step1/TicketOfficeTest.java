package chatper01.step1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@DisplayName("TicketOffice 테스트")
class TicketOfficeTest {

    private TicketOffice ticketOffice;
    private Long amount;

    @BeforeEach
    void init() {
        amount = 1000L;
        ticketOffice = new TicketOffice(amount, new ArrayList<>(Arrays.asList(new Ticket(100L), new Ticket(100L))));
    }

    @Test
    @DisplayName("티켓 한장을 꺼낸다")
    void getTicket() {
        Ticket ticket = ticketOffice.getTicket();
        assertThat(ticket.getFee()).isEqualTo(100L);
    }

    @Test
    @DisplayName("티켓 판매 금액을 수정한다")
    void buyTicket() {
        ticketOffice.plusAmount(500L);
        assertThat(ticketOffice.getAmount()).isEqualTo(amount + 500L);
        ticketOffice.minusAmount(500L);
        assertThat(ticketOffice.getAmount()).isEqualTo(amount);
    }
}
