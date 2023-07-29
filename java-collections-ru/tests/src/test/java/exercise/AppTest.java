package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> testArr = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> resultTest = App.take(testArr, 1);
        assertThat(resultTest.size()).isEqualTo(1);
        assertThat(resultTest).contains(1);
        // END
    }
}
