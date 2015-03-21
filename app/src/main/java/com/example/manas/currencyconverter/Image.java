package com.example.manas.currencyconverter;

import android.util.Log;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.bluelinelabs.logansquare.annotation.OnJsonParseComplete;
import com.bluelinelabs.logansquare.annotation.OnPreJsonSerialize;

import java.util.List;

/**
 * Created by Manas on 3/19/2015.
 * Class Model For LoganSquare Json parsing
 */
@JsonObject
public class Image {

    @JsonField
    public String NPR;




     /*
     * Optional callback method to do something when your
     * object is done parsing.
     */
    @OnJsonParseComplete
    void onParseComplete() {
        // Do some fancy post-processing stuff after parsing here
//        Log.e("Inside on parse Complete", base);
    }

    /*
     * Optional callback method to do something before your
     * object serializes.
     */
    @OnPreJsonSerialize
    void onPreSerialize() {
        // Do some fancy pre-processing stuff before serializing here
    }

}