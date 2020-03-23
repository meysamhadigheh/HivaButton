package info.meysam.hivabutton.helper;

import android.app.Application;
import android.content.Context;


/**
 * Created by meysam on 12/23/17.
 */

public class G extends Application {

    public static Context context;



    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

    }


    public String getDefaultFontName(){
        return "IRANSansMobile.ttf";
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();

        context = this;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

        context = this;
    }
}
