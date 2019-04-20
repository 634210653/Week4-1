package tw.core;

import javafx.beans.binding.When;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;
import tw.core.model.GuessResult;
import tw.core.model.Record;

import java.util.List;

import static org.mockito.Mockito.*;
import static tw.core.GameStatus.*;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {


//    @Test
//    public  void gameProcedureTest(){
//
//
//        Game game = new Game("1 2 3 5");
//
//        GuessResult guessResult = game.guess(Answer.createAnswer("1 2 4 5"));
//        Assert.assertEquals("3A0B",guessResult.getResult());
//        Assert.assertEquals(CONTINUE,game.checkStatus());
//
//        guessResult = game.guess(Answer.createAnswer("1 3 4 5"));
//        Assert.assertEquals("2A1B",guessResult.getResult());
//        Assert.assertEquals(CONTINUE,game.checkStatus());
//
//        Assert.assertTrue(game.checkCoutinue());
//
//        for(int i=0;i<4;i++) {
//            guessResult = game.guess(Answer.createAnswer("1 6 4 5"));
//            Assert.assertEquals("2A0B", guessResult.getResult());
//        }
//
//        Assert.assertEquals(FAIL,game.checkStatus());
//        Assert.assertFalse(game.checkCoutinue());
//
//        Assert.assertEquals("3A0B",game.guessHistory().get(0).getResult());
//        Assert.assertEquals("2A0B",game.guessHistory().get(5).getResult());
//
//        game = new Game("1 2 3 5");
//        Assert.assertTrue(game.checkCoutinue());
//
//        guessResult = game.guess(Answer.createAnswer("1 2 3 5"));
//        Assert.assertEquals("4A0B",guessResult.getResult());
//        Assert.assertEquals(SUCCESS,game.checkStatus());
//
//        Assert.assertFalse(game.checkCoutinue());
//    }


    @Test
    public void mainProcdureTest() {

        try {

              AnswerGenerator answerGenerator = mock(AnswerGenerator.class);
              Answer answer = mock(Answer.class);
              Answer answer2 = mock(Answer.class);
              Answer answer3 = mock(Answer.class);
              Record record = mock(Record.class);
              Record record2 = mock(Record.class);
              Record rightRecord = mock(Record.class);
              when(answerGenerator.generate()).thenReturn(answer);

              when(answer.check(answer)).thenReturn(rightRecord);
              when(answer.check(answer3)).thenReturn(record);
              when(answer.check(answer2)).thenReturn(record2);

              when(record.getValue()).thenReturn("3A1B");
              when(record2.getValue()).thenReturn("0A0B");
              when(rightRecord.getValue()).thenReturn("4A0B");


              Game game = new Game(answerGenerator);
              //test guss
              Assert.assertEquals("3A1B",game.guess(answer3).getResult());
              Assert.assertEquals("0A0B",game.guess(answer2).getResult());
              //test gussHistory
              List<GuessResult> guessResults = game.guessHistory();
              Assert.assertEquals("3A1B",guessResults.get(0).getResult());
              Assert.assertEquals("0A0B",guessResults.get(1).getResult());
              //test checkCoutinue
              Assert.assertEquals(true,game.checkCoutinue());
              game.guess(answer);
              Assert.assertEquals(false,game.checkCoutinue());
              //test ckeckStatus
              Assert.assertEquals(SUCCESS,game.checkStatus());
              game = new Game(answerGenerator);
              for(int i=0;i<6;i++) {
                  Assert.assertEquals(CONTINUE, game.checkStatus());
                  game.guess(answer3);
              }
             Assert.assertEquals(FAIL, game.checkStatus());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
