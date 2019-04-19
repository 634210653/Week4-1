package tw.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;
import tw.core.model.GuessResult;

import static tw.core.GameStatus.*;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {


    @Test
    public  void createGameTest(){

        Game game = null;
        try {
            game = new Game(new AnswerGenerator(new RandomIntGenerator()));
        }catch (Exception e) {
            String res = (e.getMessage());
            Assert.assertTrue(res.equals("Answer format is incorrect")||res.equals("Can't ask for more numbers than are available"));
        }
    }

    @Test
    public  void gameProcedureTest(){

        Game game = new Game("1 2 3 5");

        GuessResult guessResult = game.guess(Answer.createAnswer("1 2 4 5"));
        Assert.assertEquals("3A0B",guessResult.getResult());
        Assert.assertEquals(CONTINUE,game.checkStatus());

        guessResult = game.guess(Answer.createAnswer("1 3 4 5"));
        Assert.assertEquals("2A1B",guessResult.getResult());
        Assert.assertEquals(CONTINUE,game.checkStatus());

        Assert.assertTrue(game.checkCoutinue());

        for(int i=0;i<4;i++) {
            guessResult = game.guess(Answer.createAnswer("1 6 4 5"));
            Assert.assertEquals("2A0B", guessResult.getResult());
        }

        Assert.assertEquals(FAIL,game.checkStatus());
        Assert.assertFalse(game.checkCoutinue());

        Assert.assertEquals("3A0B",game.guessHistory().get(0).getResult());
        Assert.assertEquals("2A0B",game.guessHistory().get(5).getResult());

        game = new Game("1 2 3 5");
        Assert.assertTrue(game.checkCoutinue());

        guessResult = game.guess(Answer.createAnswer("1 2 3 5"));
        Assert.assertEquals("4A0B",guessResult.getResult());
        Assert.assertEquals(SUCCESS,game.checkStatus());

        Assert.assertFalse(game.checkCoutinue());
    }


}
