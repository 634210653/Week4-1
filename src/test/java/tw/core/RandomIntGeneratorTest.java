package tw.core;


import jdk.nashorn.internal.runtime.ECMAException;
import org.junit.Assert;
import org.junit.Test;
import tw.core.generator.RandomIntGenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {


    @Test
     public  void should_match_needNumber_and_maxNumber(){

        int max = 10, needNum = 4;
        RandomIntGenerator generater = new RandomIntGenerator();

        String [] result = generater.generateNums(max,needNum).split(" ");
        List<Integer> list = Stream.of(result).distinct().map(Integer::new).collect(Collectors.toList());

        //testing needNumber and max
        Assert.assertEquals(needNum, list.size());
        list.forEach((value)-> Assert.assertTrue(value<=max));

        try{
            generater.generateNums(6,7);
        }catch (Exception e){
            Assert.assertEquals("Can't ask for more numbers than are available",e.getMessage());
        }
    }


}