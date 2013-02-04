package week1;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class PrintSolutions {
    Logger logger = LoggerFactory.getLogger(PrintSolutions.class);

    Ciphertexts ciphertexts = new Ciphertexts();

    @Test
    public void Solve() {
        Solver solver = new Solver(ciphertexts.texts);
        solver.solve1();
        solver.solve2();
solver.solve3();

        logger.info("Solution: {}", solver.solve4());

//        for (Map.Entry<byte[], char[]> entry: solver.solutions.entrySet()) {
//            logger.info("Solution: {}||", new String(entry.getValue() ));
//        }
    }

}
