package com.vojtechcahlik.spaceintact;

import java.util.ArrayList;

public abstract class HeadUpDisplay {
    
    public final ArrayList<Sprite> SYMBOLS = new ArrayList<>();

    abstract public void update();
    
}