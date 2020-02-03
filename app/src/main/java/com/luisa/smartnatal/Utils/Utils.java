package com.luisa.smartnatal.Utils;

import android.util.Log;

import java.util.Random;

public class Utils {

    public static int RandomNum( ){

        Random r = new Random();
        int randomInt = r.nextInt(100) + 1;
        Log.e("randomnum", String.valueOf(randomInt));

        return randomInt;
    }
}
