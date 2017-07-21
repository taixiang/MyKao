package com.kaoyan;

import com.kaoyan.api.RetrofitService;
import com.kaoyan.utils.CommonUtil;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.view.IMainPresenter;
import com.kaoyan.view.IMainView;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() throws Exception{
        CommonUtil.isfastdoubleClick();
        LogUtil.i("TEST","test");
    }

}