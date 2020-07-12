package org.gaborbalazs.smartplatform.lotteryservice.betdao.factory;

import org.apache.commons.lang3.StringUtils;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.Hit;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.ScandinavianDraw;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Component
public class ScandinavianDrawFactory {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd.");
    private static final String CSV_DELIMITER = ";";

    public ScandinavianDraw create(String csvLine) {
        String[] values = csvLine.split(CSV_DELIMITER);
        return ScandinavianDraw.newScandinavianDraw()
                .year(Integer.parseInt(values[0]))
                .week(Integer.parseInt(values[1]))
                .date(StringUtils.isBlank(values[2]) ? null : LocalDate.parse(values[2], DATE_TIME_FORMATTER))
                .hits(List.of(
                        Hit.newHit()
                                .hits(7)
                                .tickets(Integer.parseInt(values[3]))
                                .prize(Long.parseLong((values[4].substring(0, values[4].length() - 3)).replaceAll(" ", "")))
                                .currency(Currency.getInstance("HUF"))
                                .build(),
                        Hit.newHit()
                                .hits(6)
                                .tickets(Integer.parseInt(values[5]))
                                .prize(Long.parseLong((values[6].substring(0, values[6].length() - 3)).replaceAll(" ", "")))
                                .currency(Currency.getInstance("HUF"))
                                .build(),
                        Hit.newHit()
                                .hits(5)
                                .tickets(Integer.parseInt(values[7]))
                                .prize(Long.parseLong((values[8].substring(0, values[8].length() - 3)).replaceAll(" ", "")))
                                .currency(Currency.getInstance("HUF"))
                                .build(),
                        Hit.newHit()
                                .hits(4)
                                .tickets(Integer.parseInt(values[9]))
                                .prize(Long.parseLong((values[10].substring(0, values[10].length() - 3)).replaceAll(" ", "")))
                                .currency(Currency.getInstance("HUF"))
                                .build()))
                .drawnNumbersMechanical(new TreeSet<>(Set.of(
                        Integer.parseInt(values[11]),
                        Integer.parseInt(values[12]),
                        Integer.parseInt(values[13]),
                        Integer.parseInt(values[14]),
                        Integer.parseInt(values[15]),
                        Integer.parseInt(values[16]),
                        Integer.parseInt(values[17]))))
                .drawnNumbersManual(new TreeSet<>(Set.of(
                        Integer.parseInt(values[18]),
                        Integer.parseInt(values[19]),
                        Integer.parseInt(values[20]),
                        Integer.parseInt(values[21]),
                        Integer.parseInt(values[22]),
                        Integer.parseInt(values[23]),
                        Integer.parseInt(values[24]))))
                .build();
    }
}
