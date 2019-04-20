package tw.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;
import tw.core.model.Record;
import tw.io.MyIO;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;
import static tw.core.GameStatus.*;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {


    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private  String newline = System.getProperty("line.separator");
    @Before
    public   void setUp(){

        System.setOut(new PrintStream(outContent));

    }

    private String output(){
        return  outContent.toString();
    }

    @Test
    public void mainProcdureTest(){

           try{
                AnswerGenerator answerGenerator = mock(AnswerGenerator.class);
                Answer answer0 = mock(Answer.class);
                Answer answer1 = mock(Answer.class);
                Answer answer2 = mock(Answer.class);
                when(answer0.toString()).thenReturn("1 2 3 4");
                when(answer1.toString()).thenReturn("2 1 3 4");
                when(answer2.toString()).thenReturn("1 2 3 4");

                Record record1 = mock(Record.class);
                Record record2 = mock(Record.class);
                when(answer0.check(answer1)).thenReturn(record1);
                when(answer0.check(answer0)).thenReturn(record2);

                when(record1.getValue()).thenReturn("3A1B");
                when(record2.getValue()).thenReturn("4A0B");

                when(answerGenerator.generate()).thenReturn(answer0);

                Game game = spy(new Game(answerGenerator));
                GameView gameView = spy( new GameView());
                doNothing().when(gameView).showBegin();

                GameController gameController = new GameController(game,gameView);

                gameController.beginGame();
                Assert.assertEquals("", output());

                InputCommand inputCommend0 =  mock(InputCommand.class);
                InputCommand inputCommend1=  mock(InputCommand.class);

                //测试猜完6次
                when(inputCommend0.input()).thenReturn(answer1);
                when(inputCommend1.input()).thenReturn(answer0);
                gameController.play(inputCommend0);

                //测试猜成功
                game = spy(new Game(answerGenerator));

                gameController = new GameController(game,gameView);
                gameController.play(inputCommend1);

                Assert.assertEquals(MyIO.getData(String.format("./data/res.txt")), output());

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                outContent.reset();
            }
    }
}