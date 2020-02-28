package lotto;

import domain.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {

    @Test
    void 구매금액으로_로또_Tickets의_사이즈_확인() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        PurchaseAmount amount = new PurchaseAmount("10800");
        int totalLottoCount = amount.getCount();
        List<String> numbers = Arrays.asList("1","2","3","4","5","6");
        List<List<String>> manualNumbers = new ArrayList<>();
        manualNumbers.add(numbers);
        ManualLottoTickets manualLottoTickets = new ManualLottoTickets(manualNumbers);
        LottoCount lottoCount = new LottoCount(totalLottoCount, "1");

        LottoTickets lottoTickets = new LottoTickets(LottoFactory.createLottoTickets(lottoCount, randomNumberGenerator, manualLottoTickets));
        assertThat(lottoTickets.getTicketsSize()).isEqualTo(10);
    }

    @Test
    void 로또_등수가_제대로_더해졌는지_테스트() {
        Set<LottoNumber> lotto1 = new HashSet<>();
        lotto1.add(LottoNumber.valueOf(1));
        lotto1.add(LottoNumber.valueOf(2));
        lotto1.add(LottoNumber.valueOf(3));
        lotto1.add(LottoNumber.valueOf(4));
        lotto1.add(LottoNumber.valueOf(11));
        lotto1.add(LottoNumber.valueOf(12));
        Lotto lotto_1 = new Lotto(lotto1);

        Set<LottoNumber> lotto2 = new HashSet<>();
        lotto2.add(LottoNumber.valueOf(1));
        lotto2.add(LottoNumber.valueOf(2));
        lotto2.add(LottoNumber.valueOf(3));
        lotto2.add(LottoNumber.valueOf(4));
        lotto2.add(LottoNumber.valueOf(5));
        lotto2.add(LottoNumber.valueOf(7));
        Lotto lotto_2 = new Lotto(lotto2);

        List<Lotto> tempLottoTickets = new ArrayList<>();
        tempLottoTickets.add(lotto_1);
        tempLottoTickets.add(lotto_2);

        String[] winningNumber = {"1","2","3","4","5","6"};
        WinningNumber winningNumbers = new WinningNumber(winningNumber, "7");

        LottoTickets lottoTickets = new LottoTickets(tempLottoTickets);
        LottoResult lottoResult = lottoTickets.countWinningLotto(winningNumbers);


        Map<LottoRank, Integer> testMap = new HashMap<>();
        testMap.put(LottoRank.FIRST, 0);
        testMap.put(LottoRank.SECOND, 1);
        testMap.put(LottoRank.THIRD, 0);
        testMap.put(LottoRank.FOURTH, 1);
        testMap.put(LottoRank.FIFTH, 0);

        LottoResult testResult = new LottoResult(testMap);

        assertThat(lottoResult).isEqualTo(testResult);
    }
}
