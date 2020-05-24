package com.bear.screenshotdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bear.screenshot.ScreenShotTools
import com.bear.screenshot.model.ScreenBitmap
import com.bear.screenshot.model.i.IScreenShotCallBack
import kotlinx.android.synthetic.main.activity_recycler_view.*

/**
 * description:
 * author: bear .
 * Created date:  2019-06-26.
 * mail:2280885690@qq.com
 */
class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
//        tv.setOnClickListener {
//            ScreenShotTools.instance.takeCapture(this, recycler_view, object : IScreenShotCallBack {
//                override fun onResult(screenBitmap: ScreenBitmap?) {
//                    screenBitmap?.let {
//                        ShowScreenImageActivity.action(this@RecyclerViewActivity,it.filePath)
//                    }
//                }
//
//            })
//        }
        loadData()
    }

    private fun loadData() {
        mask.setOnClickListener {
            mask.visibility = View.GONE

        }
        val list = ArrayList<String>()
        for (x in 0..50) {
            list.add(x.toString())
        }
        val adapter = CustomAdapter(list) {
            val hi = recycler_view.layoutManager?.findViewByPosition(it) ?: return@CustomAdapter
            val postion = IntArray(2)
            hi.getLocationOnScreen(postion)
            ScreenShotTools.instance.takeCapture(this, hi, object : IScreenShotCallBack {
                override fun onResult(screenBitmap: ScreenBitmap?) {
                    screenBitmap?.let {

                        mask.post {
                            Runnable {
                                mask.visibility = View.VISIBLE
                                mask.setDraw(it.bitmap, postion)
                            }.run()

                        }
//                        ShowScreenImageActivity.action(this@RecyclerViewActivity,it.filePath)

                    }
                }

            })

        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }


    companion object {
        @JvmStatic
        fun action(context: Context) {
            val intent = Intent(context, RecyclerViewActivity::class.java)
            context.startActivity(intent)
        }
    }
}