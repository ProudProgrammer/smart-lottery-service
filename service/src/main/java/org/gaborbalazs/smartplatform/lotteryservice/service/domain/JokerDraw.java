package org.gaborbalazs.smartplatform.lotteryservice.service.domain;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public final class JokerDraw implements Draw {

    private final int year;
    private final int week;
    private final LocalDate date;
    private final List<Hit> hits;
    private final List<Integer> drawnNumbers;

    private JokerDraw(Builder builder) {
        this.year = builder.year;
        this.week = builder.week;
        this.date = builder.date;
        this.hits = builder.hits;
        this.drawnNumbers = builder.drawnNumbers;
    }

    public static Builder newJokerDraw() {
        return new Builder();
    }

    public int getYear() {
        return year;
    }

    public int getWeek() {
        return week;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public List<Integer> getDrawnNumbers() {
        return Collections.unmodifiableList(drawnNumbers);
    }

    @Override
    public String toString() {
        return "JokerDraw{" +
                "year=" + year +
                ", week=" + week +
                ", date=" + date +
                ", hits=" + hits +
                ", drawnNumbers=" + drawnNumbers +
                '}';
    }

    public static final class Builder {
        private int year;
        private int week;
        private LocalDate date;
        private List<Hit> hits;
        private List<Integer> drawnNumbers;

        private Builder() {
        }

        public JokerDraw build() {
            return new JokerDraw(this);
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder week(int week) {
            this.week = week;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder hits(List<Hit> hits) {
            this.hits = hits;
            return this;
        }

        public Builder drawnNumbers(List<Integer> drawnNumbers) {
            this.drawnNumbers = drawnNumbers;
            return this;
        }
    }
}
