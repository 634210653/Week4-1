package tw.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tw.commands.GuessInputCommand;
import tw.core.Game;
import tw.io.MyIO;
import tw.views.GameView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

        for(int i=1;i<3;i++) {

            String data  = MyIO.getData("./data/input"+i+".txt");
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            GameController controller = new GameController(new Game("1 2 3 4"), new GameView());

            try {
                controller.beginGame();
                String shouldOutput = "------Guess Number Game, You have 6 chances to guess!  ------" + newline;
                Assert.assertEquals(shouldOutput, output());

                controller.play(new GuessInputCommand());
                Assert.assertEquals(MyIO.getData(String.format("./data/res%d.txt",i)), output());

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                outContent.reset();
            }
        }
    }
}