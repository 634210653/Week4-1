package tw.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {

    @Test
    public void  validateTest(){

        try {
            Answer.createAnswer("1 2 3 10").validate();
        }catch(Exception e){
            Assert.assertEquals("Answer format is incorrect",e.getMessage());
        }
    }

    @Test
    public void createAnswerTest(){

        Assert.assertEquals("1 2 3 4",  Answer.createAnswer("1 2 3 4").toString());
        Assert.assertEquals("1 2 3 10",  Answer.createAnswer("1 2 3 10").toString());
    }

    @Test
    public  void checkTest(){

        String str1 = "1 2 3 4";
        String str2 = "4 5 6 7";
        Answer answer = Answer.createAnswer(str1);
        Assert.assertEquals("4A0B",answer.check(Answer.createAnswer(str1)).getValue());
        Assert.assertEquals("0A1B",answer.check(Answer.createAnswer(str2)).getValue());
    }

    @Test
    public void getIndexOfNumTest(){

        Answer answer = Answer.createAnswer("4 5 6 7");
        Assert.assertEquals(0,answer.getIndexOfNum("4"));
        Assert.assertEquals(1,answer.getIndexOfNum("5"));
        Assert.assertEquals(2,answer.getIndexOfNum("6"));
        Assert.assertEquals(3,answer.getIndexOfNum("7"));
    }

    @Test
    public  void setNumListTest(){

        Answer answer = Answer.createAnswer("4 5 6 7");
        answer.setNumList( Stream.of(new String[] {"4","6","5","7"}).collect(Collectors.toList()));
        Assert.assertEquals("4 6 5 7",answer.toString());
        Assert.assertFalse("4 5 6 7".equals(answer.toString()));

    }
}