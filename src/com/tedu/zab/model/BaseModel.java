package com.tedu.zab.model;

import bf.cloud.hybrid.TNApplication;
import android.content.Context;

public abstract class BaseModel {
	
	public int CODE_NULL=1000;
	
    public static int CODE_NOT_EQUAL=1001;

    public static final int DEFAULT_LIMIT=20;

    public Context getContext(){
        return TNApplication.INSTANCE();
    }
}
