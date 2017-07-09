package infrastructure.time;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ivan on 09/07/2017.
 */
public class ClockTest {
    @Test
    public void should_return_the_date_of_today() throws Exception {
        //When
        LocalDate today = new Clock().today();

        //Then
        assertThat(LocalDate.now()).isEqualTo(today);
    }

}