package chatper01.step1;

public class Bag {
    private Long amount;
    private Invitation invitation;
    private Ticket ticket;

    public Bag(Long amount, Invitation invitation) {
        this.amount = amount;
        this.invitation = invitation;
    }

    public Bag(Long amount) {
        this(amount,null);
    }

    public Boolean hasInvitation() {
        return invitation != null;
    }

    public Boolean hasTicket() {
        return ticket != null;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
