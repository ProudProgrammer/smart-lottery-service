package org.gaborbalazs.smartplatform.lotteryservice.betdao.adapter;

import org.gaborbalazs.smartplatform.lotteryservice.betdao.repository.BetDao;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.FiveOutOfNinetyDraw;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.ScandinavianDraw;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.SixOutOfFortyFiveDraw;
import org.gaborbalazs.smartplatform.lotteryservice.service.retrieve.iface.RetrieveDrawnNumbersService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BetDaoAdapter implements RetrieveDrawnNumbersService {

    private final BetDao betDao;

    BetDaoAdapter(BetDao betDao) {
        this.betDao = betDao;
    }

    @Override
    public List<FiveOutOfNinetyDraw> retrieveAllFiveOutOfNinetyDraws() {
        return betDao.getAllFiveOutOfNinetyDraws();
    }

    @Override
    public List<SixOutOfFortyFiveDraw> retrieveAllSixOutOfFortyFiveDraws() {
        return betDao.getAllSixOutOfFortyFiveDraws();
    }

    @Override
    public List<ScandinavianDraw> retrieveAllScandinavianDraws() {
        return betDao.getAllScandinavianDraws();
    }
}
