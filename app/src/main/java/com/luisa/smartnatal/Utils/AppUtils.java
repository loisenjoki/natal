package com.luisa.smartnatal.Utils;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

public class AppUtils {

    public static int RandomNum( ){

        Random r = new Random();
        int randomInt = r.nextInt(100) + 1;
        Log.e("randomnum", String.valueOf(randomInt));

        return randomInt;
    }

    public static boolean validated(View... views) {
        boolean validated = true;

        for (View v : views) {
            if (v instanceof EditText) {
                if (TextUtils.isEmpty(((EditText) v).getText().toString())) {
                    validated = false;
                    ((EditText) v).setError("Required");
                }
            }
        }
        return validated;
    }


}
