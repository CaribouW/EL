package Tmp_lib;

import android.content.Context;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;

/**
 * Created by sdj on 2018/5/25.
 */

public class myDatePickDialog extends DatePickDialog{
    public myDatePickDialog(Context context) {
        super(context);
    }
    public String getText(){
        return "123";
    }


}
