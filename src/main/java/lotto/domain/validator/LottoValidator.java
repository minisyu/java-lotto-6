package lotto.domain.validator;

import java.util.List;
import lotto.domain.lotto.BonusNumber;
import lotto.domain.lotto.Lotto;

public class LottoValidator {
    public static final int LOTTO_NUMBERS = 6;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    // 로또 번호가 6개인지 검증 -> Lotto 클래스
    public static void verifyNumbersSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBERS) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    // 6자리 숫자에 중복이 있는지 검증 -> Lotto 클래스
    public static void verifyNoDuplication(List<Integer> numbers) {
        int uniqueCount = getUniqueCount(numbers);
        if (uniqueCount != numbers.size()) {
            throw new IllegalArgumentException("로또 번호에 중복되는 숫자가 있으면 안 됩니다.");
        }
    }

    private static int getUniqueCount(List<Integer> numbers) {
        return (int) numbers.stream().distinct().count();
    }

    // 로또 번호 범위 설정 -> Lotto 클래스
    public static void verifyLottoNumberRange(List<Integer> numbers) {
        if (isOutOfRange(numbers)) {
            throw new IllegalArgumentException("로또 번호는 1에서 45사이의 숫자만 가능합니다.");
        }
    }

    private static boolean isOutOfRange(List<Integer> numbers) {
        return numbers.stream().anyMatch(number -> number < MIN_NUMBER || number > MAX_NUMBER);
    }

    // 로또 범위 설정 -> BonusNumber 클래스
    public static void verifyRange(int bonusNumber) {
        if (bonusNumber < MIN_NUMBER || bonusNumber > MAX_NUMBER) {
            throw new IllegalArgumentException("보너스 번호는 1에서 45 사이에 있어야 합니다.");
        }
    }

    // 보너스 번호가 당첨 번호와 중복되는지 검증 -> BonusNumber 클래스
    public static void verifyBonusIsNotContainWinningNumbers(Lotto winningNumbers, BonusNumber bonusNumber) {
        if (hasBonusNumber(winningNumbers, bonusNumber)) {
            throw new IllegalArgumentException("입력한 당첨번호와 동일한 숫자를 입력하면 안 됩니다.");
        }
    }

    private static boolean hasBonusNumber(Lotto winningNumbers, BonusNumber bonusNumber) {
        List<Integer> lottoNumbers = winningNumbers.getLottoNumbers();
        return lottoNumbers.contains(bonusNumber.getBonusNumber());
    }
}
