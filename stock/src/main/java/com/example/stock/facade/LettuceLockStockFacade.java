package com.example.stock.facade;

import com.example.stock.repository.RedisLockRepository;
import com.example.stock.service.StockService;
import org.springframework.stereotype.Service;

@Service
public class LettuceLockStockFacade {

    private RedisLockRepository redisLockRepository;

    private StockService stockService;

    public LettuceLockStockFacade(RedisLockRepository redisLockRepository, StockService stockService) {
        this.redisLockRepository = redisLockRepository;
        this.stockService = stockService;
    }

    public void decrease(Long key, Long quantity) throws InterruptedException {
        while (!redisLockRepository.lock(key)) {
            Thread.sleep(100);
        } // lock 획득까지 대기 && sleep을 통해서 redis로 가는 부하 저하

        try{ // 락을 획득했으면 재고 서비스 로직을 실행하고
            stockService.decrease(key, quantity);
        } finally { // 끝나면 unlock
            redisLockRepository.unlock(key);
        }
    }
}
