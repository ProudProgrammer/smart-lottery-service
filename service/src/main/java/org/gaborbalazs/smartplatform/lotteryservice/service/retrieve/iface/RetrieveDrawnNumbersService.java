package org.gaborbalazs.smartplatform.lotteryservice.service.retrieve.iface;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.Draw;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;

import java.util.List;

public interface RetrieveDrawnNumbersService {

    List<Draw> retrieveAllByLotteryType(LotteryType lotteryType);
}
