package org.gaborbalazs.smartplatform.lotteryservice.service.domain;

import java.util.Currency;

public final class Hit {

    private final int hits;
    private final int tickets;
    private final long prize;
    private final Currency currency;

    private Hit(Builder builder) {
        this.hits = builder.hits;
        this.tickets = builder.tickets;
        this.prize = builder.prize;
        this.currency = builder.currency;
    }

    public int getHits() {
        return hits;
    }

    public int getTickets() {
        return tickets;
    }

    public long getPrize() {
        return prize;
    }

    public Currency getCurrency() {
        return currency;
    }

    public static Builder newHit() {
        return new Builder();
    }

    public static final class Builder {

        private int hits;
        private int tickets;
        private long prize;
        private Currency currency;

        private Builder() {
        }

        public Hit build() {
            return new Hit(this);
        }

        public Builder hits(int hits) {
            this.hits = hits;
            return this;
        }

        public Builder tickets(int tickets) {
            this.tickets = tickets;
            return this;
        }

        public Builder prize(long prize) {
            this.prize = prize;
            return this;
        }

        public Builder currency(Currency currency) {
            this.currency = currency;
            return this;
        }
    }
}
