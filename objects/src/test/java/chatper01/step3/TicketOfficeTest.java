package chatper01.step3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("리팩토링한 TicketOffice 테스트")
class TicketOfficeTest {

    private TicketOffice ticketOffice;
    private Audience audience;
    private Long ticketOfficeAmount;
    private Ticket ticket;
    private Long fee;
    private Long amount;

    @BeforeEach
    void init() {
        ticketOfficeAmount = 1000L;
        amount = 600L;
        fee = 500L;
        ticket = new Ticket(fee);
        ticketOffice = new TicketOffice(ticketOfficeAmount, new ArrayList<>(Arrays.asList(ticket, ticket, ticket)));
    }

    @Test
    @DisplayName("초대장이 있는 관객에게 티켓을 무료로 판다")
    void sellTicketToAudienceWithInvitation() {
        // given
        audience = new Audience(new Bag(amount, new Invitation()));
        // when
        ticketOffice.sellTicketTo(audience);
        // then
        assertThat(ticketOffice.getAmount()).isEqualTo(ticketOfficeAmount);
        assertThat(audience.getBag().getAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("초대장이 없는 관객에게 티켓을 팔고 티켓가격을 얻는다.")
    void sellTicketToAudienceWithNoInvitation() {
        // given
        audience = new Audience(new Bag(amount));
        // when
        ticketOffice.sellTicketTo(audience);
        // then
        assertThat(ticketOffice.getAmount()).isEqualTo(ticketOfficeAmount + fee);
        assertThat(audience.getBag().getAmount()).isEqualTo(amount - fee);
    }
}
