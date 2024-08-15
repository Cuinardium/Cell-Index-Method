package ar.edu.itba.ss.g2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ar.edu.itba.ss.g2.state.Input;
import ar.edu.itba.ss.g2.state.Output;
import ar.edu.itba.ss.g2.utils.FileUtil;

import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class AppTest {
    @TempDir static File tempDir;

    public static Stream<Integer> provideM() {
        return Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
    }

    @ParameterizedTest
    @MethodSource("provideM")
    public void testApp(int M) throws IOException {
        Input input = FileUtil.deserializeInput("src/test/resources");

        var a = CellIndexMethod.calculate(input.getParticles(), input.getL(), (long) M, 1.0, false);

        String expectedOutputFile = "src/test/resources/output.txt";

        FileUtil.serializeOutput(new Output(a, 0), tempDir.getAbsolutePath());

        File expectedOutput = new File(expectedOutputFile);

        File actualOutput = new File(tempDir.getAbsolutePath() + "/output.txt");

        try (BufferedReader expectedReader = new BufferedReader(new FileReader(expectedOutput));
                BufferedReader actualReader = new BufferedReader(new FileReader(actualOutput))) {

            String expectedLine;
            String actualLine;

            while ((expectedLine = expectedReader.readLine()) != null) {
                actualLine = actualReader.readLine();
                assertEquals(expectedLine, actualLine);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("provideM")
    public void testAppToroidal(int M) throws IOException {
        Input input = FileUtil.deserializeInput("src/test/resources");

        var a = CellIndexMethod.calculate(input.getParticles(), input.getL(), (long) M, 1.0, true);

        String expectedOutputFile = "src/test/resources/output-toroidal.txt";

        FileUtil.serializeOutput(new Output(a, 0), tempDir.getAbsolutePath());

        File expectedOutput = new File(expectedOutputFile);

        File actualOutput = new File(tempDir.getAbsolutePath() + "/output.txt");

        try (BufferedReader expectedReader = new BufferedReader(new FileReader(expectedOutput));
                BufferedReader actualReader = new BufferedReader(new FileReader(actualOutput))) {

            String expectedLine;
            String actualLine;

            while ((expectedLine = expectedReader.readLine()) != null) {
                actualLine = actualReader.readLine();
                assertEquals(expectedLine, actualLine);
            }
        }
    }
}
