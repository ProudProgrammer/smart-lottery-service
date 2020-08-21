package org.gaborbalazs.smartplatform.lotteryservice.betdao.adapter;

import org.gaborbalazs.smartplatform.lotteryservice.betdao.repository.DrawDao;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.Draw;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.retrieve.iface.RetrieveDrawnNumbersService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BetDaoAdapter implements RetrieveDrawnNumbersService {

    private final DrawDao drawDao;

    BetDaoAdapter(DrawDao drawDao) {
        this.drawDao = drawDao;
    }

    @Override
    @Cacheable("drawnLotteryNumbers")
    public List<Draw> retrieveAllByLotteryType(LotteryType lotteryType) {
        return drawDao.findAllByLotteryType(lotteryType);
    }
}
