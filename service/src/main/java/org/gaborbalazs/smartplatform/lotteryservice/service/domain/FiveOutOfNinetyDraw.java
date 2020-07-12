package org.gaborbalazs.smartplatform.lotteryservice.service.domain;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

public final class FiveOutOfNinetyDraw implements Draw {

    private final int year;
    private final int week;
    private final LocalDate date;
    private final List<Hit> hits;
    private final SortedSet<Integer> drawnNumbers;

    private FiveOutOfNinetyDraw(Builder builder) {
        this.year = builder.year;
        this.week = builder.week;
        this.date = builder.date;
        this.hits = builder.hits;
        this.drawnNumbers = builder.drawnNumbers;
    }

    public static Builder newFiveOutOfNinetyDraw() {
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

    public SortedSet<Integer> getDrawnNumbers() {
        return Collections.unmodifiableSortedSet(drawnNumbers);
    }

    @Override
    public String toString() {
        return "FiveOutOfNinetyDraw{" +
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
        private SortedSet<Integer> drawnNumbers;

        private Builder() {
        }

        public FiveOutOfNinetyDraw build() {
            return new FiveOutOfNinetyDraw(this);
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

        public Builder drawnNumbers(SortedSet<Integer> drawnNumbers) {
            this.drawnNumbers = drawnNumbers;
            return this;
        }
    }
}
