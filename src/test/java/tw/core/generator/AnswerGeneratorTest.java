package tw.core.generator;

import org.junit.Assert;
import org.junit.Test;
import tw.core.Answer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {

    @Test
    public void generateTest(){

        AnswerGenerator generator = new AnswerGenerator(new RandomIntGenerator());

        for(int i=0; i< 10;i++){
            try {
                Answer answer = generator.generate();
                String [] result = answer.toString().split(" ");
                List<Integer> list = Stream.of(result).distinct().map(Integer::new).collect(Collectors.toList());
                //testing needNumber and max
                Assert.assertEquals(4, list.size());
                list.forEach((value)-> Assert.assertTrue(value<=9));
            }catch(Exception e){
               Assert.assertEquals("Answer format is incorrect",e.getMessage());
            }
        }
    }
}

