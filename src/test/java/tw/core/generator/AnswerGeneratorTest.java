package tw.core.generator;

import org.junit.Assert;
import org.junit.Test;
import tw.core.Answer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {

    @Test
    public void generateTest(){



        try {
            RandomIntGenerator randomIntGenerator = mock(RandomIntGenerator.class);
            AnswerGenerator generator = new AnswerGenerator(randomIntGenerator);
            String answerStr = "1 2 3 4";
            when(randomIntGenerator.generateNums(9,4)).thenReturn(answerStr);
            Assert.assertEquals(generator.generate().toString(),answerStr);
            String answerStr2 = "1 2 3 11";
            when(randomIntGenerator.generateNums(9,4)).thenReturn(answerStr2);
            generator.generate();
        }catch (Exception e) {
            Assert.assertEquals("Answer format is incorrect",e.getMessage());
        }
    }
}

