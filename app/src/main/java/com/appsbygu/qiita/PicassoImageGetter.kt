package com.appsbygu.qiita

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.Html
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class PicassoImageGetter : Html.ImageGetter {

    private val textView: TextView
    private val picasso: Picasso

    constructor(picasso: Picasso, textView: TextView){
        this.textView = textView
        this.picasso = picasso
    }


    override fun getDrawable(sourece: String): Drawable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class BitmapDrawablePlaceHolder : BitmapDrawable(), Target{

        override fun draw(canvas: Canvas?) {
            super.draw(canvas)
        }

        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}