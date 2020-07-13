package org.gaborbalazs.smartplatform.lotteryservice.service.retrieve.iface;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.FiveOutOfNinetyDraw;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.JokerDraw;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.ScandinavianDraw;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.SixOutOfFortyFiveDraw;

import java.util.List;

public interface RetrieveDrawnNumbersService {

    List<FiveOutOfNinetyDraw> retrieveAllFiveOutOfNinetyDraws();

    List<SixOutOfFortyFiveDraw> retrieveAllSixOutOfFortyFiveDraws();

    List<ScandinavianDraw> retrieveAllScandinavianDraws();

    List<JokerDraw> retrieveAllJokerDraws();
}
