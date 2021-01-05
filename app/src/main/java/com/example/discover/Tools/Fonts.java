package com.example.discover.Tools;

import android.content.Context;
import android.graphics.Typeface;

public class Fonts {
    Context context;
    public Fonts(Context context){
        this.context = context;
    }

    public Typeface getAudioWide(){
        return Typeface.createFromAsset(context.getAssets(),"Fonts/Audiowide-Regular.ttf");
    }

    public Typeface getMontserratAlternates(){
        return Typeface.createFromAsset(context.getAssets(),"Fonts/MontserratAlternates-Regular.ttf");
    }

    public Typeface getNewsCycle(){
        return Typeface.createFromAsset(context.getAssets(),"Fonts/BalooTamma2-Regular.ttf");
    }
}


