package tw.core;

import org.junit.Assert;
import org.junit.Test;
import tw.validator.InputValidator;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {

    @Test
    public  void inputValidatorTest(){

        InputValidator validator = new InputValidator();

        Assert.assertFalse(validator.validate("1 1 1 1"));
        Assert.assertFalse(validator.validate("1 2 3 11"));
        Assert.assertFalse(validator.validate("1 2 4"));
        Assert.assertFalse(validator.validate("1 2 4 5 6"));
        Assert.assertTrue(validator.validate("1 2 4 5 "));



    }
}
