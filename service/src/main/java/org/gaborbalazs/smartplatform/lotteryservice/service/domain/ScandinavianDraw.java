package org.gaborbalazs.smartplatform.lotteryservice.service.domain;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

public final class ScandinavianDraw implements Draw {

    private final int year;
    private final int week;
    private final LocalDate date;
    private final List<Hit> hits;
    private final SortedSet<Integer> drawnNumbersMechanical;
    private final SortedSet<Integer> drawnNumbersManual;

    private ScandinavianDraw(Builder builder) {
        this.year = builder.year;
        this.week = builder.week;
        this.date = builder.date;
        this.hits = builder.hits;
        this.drawnNumbersMechanical = builder.drawnNumbersMechanical;
        this.drawnNumbersManual = builder.drawnNumbersManual;
    }

    public static Builder newScandinavianDraw() {
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

    public SortedSet<Integer> getDrawnNumbersMechanical() {
        return Collections.unmodifiableSortedSet(drawnNumbersMechanical);
    }

    public SortedSet<Integer> getDrawnNumbersManual() {
        return Collections.unmodifiableSortedSet(drawnNumbersManual);
    }

    @Override
    public String toString() {
        return "ScandinavianDrawn{" +
                "year=" + year +
                ", week=" + week +
                ", date=" + date +
                ", hits=" + hits +
                ", drawnNumbersMechanical=" + drawnNumbersMechanical +
                ", drawnNumbersManual=" + drawnNumbersManual +
                '}';
    }

    public static final class Builder {
        private int year;
        private int week;
        private LocalDate date;
        private List<Hit> hits;
        private SortedSet<Integer> drawnNumbersMechanical;
        private SortedSet<Integer> drawnNumbersManual;

        private Builder() {
        }

        public ScandinavianDraw build() {
            return new ScandinavianDraw(this);
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

        public Builder drawnNumbersMechanical(SortedSet<Integer> drawnNumbersMechanical) {
            this.drawnNumbersMechanical = drawnNumbersMechanical;
            return this;
        }

        public Builder drawnNumbersManual(SortedSet<Integer> drawnNumbersManual) {
            this.drawnNumbersManual = drawnNumbersManual;
            return this;
        }
    }
}
