package com.bear.screenshotdemo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.Window

class MaskView : View {
    private var bitmap:Bitmap?=null
    private var positon:IntArray?=null

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        // ...
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {}

    init {
//        Rect rectangle= new Rect();
//        getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
//        高度为rectangle.top-0仍为rectangle.top
//        Log.e("WangJ", "状态栏-方法3:" + rectangle.top);
        val rect = Rect()


    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        if(Math.random()>=0.5){
//            canvas?.drawColor(Color.RED)
//        }else{
//            canvas?.drawColor(Color.GREEN)
//        }

            canvas?.drawColor(Color.parseColor("#66666666"))
        bitmap?.let {

            canvas?.drawBitmap(bitmap,positon?.get(0)?.toFloat() ?: 0f
                ,positon?.get(1)?.toFloat()?.minus(262f) ?: 0f,null)
        }
    }

    public fun setDraw(bitmap:Bitmap?,positon:IntArray){
        this.bitmap=bitmap
        this.positon=positon

    }
}
