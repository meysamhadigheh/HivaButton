package info.meysam.hivabuttontest.activities

import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import info.meysam.hivaadapter.HivaRecyclerAdapter
import info.meysam.hivabutton.views.HivaButton
import info.meysam.hivabuttontest.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : ParentActivity() {


    override fun setContentViewActivity() {

        setContentView(R.layout.activity_main)

    }

    override fun getIntentData() {

    }

    override fun instantiateViews() {

    }

    override fun setViewListeners() {

    }

    override fun setActivityContent() {

        hiva_button.setOnClickListener(clickListener)
        hiva_button2.setOnClickListener(clickListener)
        hiva_button3.setOnClickListener(clickListener)
        hiva_button4.setOnClickListener(clickListener)
        hiva_button_top.setOnClickListener(clickListener)
        hiva_button_bottom.setOnClickListener(clickListener)
        hiva_button_nobg.setOnClickListener(clickListener)
        //findViewById(R.id.circle).setOnClickListener(clickListener);

        //findViewById(R.id.circle).setOnClickListener(clickListener);
        hiva_button_nobg.isEnabled = false
        //findViewById(R.id.circle).setEnabled(false);

        //findViewById(R.id.circle).setEnabled(false);
        val adapter = HivaRecyclerAdapter()
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = adapter


        /*for (i in 0..29) {
            adapter.addItem(ButtonModel("دکمه شماره #$i"))
        }*/

        adapter.notifyDataSetChanged()





        Glide.with(this)
            .asBitmap()
            .load("http://www.otooapp.com/image/200x200/k7k2VjjR.png")
            .into(object : SimpleTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    hiva_button.setIcon(0)
                    hiva_button.setIcon(resource)
                }


            })

    }

    override fun refreshContent() {

    }

    var clickListener =
        View.OnClickListener { v -> startLoading(v) }

    private fun startLoading(button: View) {
        if (button == hiva_button) {
            (findViewById<HivaButton>(R.id.circle)).toggle()
            return
        }
        (button as HivaButton).startLoadingState()
        button.postDelayed({ button.stopLoadingState() }, 3000)
    }
}
