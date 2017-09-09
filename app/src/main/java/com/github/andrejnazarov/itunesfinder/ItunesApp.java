package com.github.andrejnazarov.itunesfinder;

import android.app.Application;

import com.github.andrejnazarov.itunesfinder.dagger.AppModule;
import com.github.andrejnazarov.itunesfinder.dagger.DaggerNetComponent;
import com.github.andrejnazarov.itunesfinder.dagger.NetComponent;
import com.github.andrejnazarov.itunesfinder.dagger.NetModule;

/**
 * @author Nazarov on 09.09.17.
 */

public class ItunesApp extends Application {

    private static final String BASE_URL = "https://itunes.apple.com/";
    private static NetComponent sNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BASE_URL))
                .build();
    }

    public static NetComponent getNetComponent() {
        return sNetComponent;
    }
}