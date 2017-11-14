package alex.com.darkskyapp.components.app;

import android.app.Application;

import com.facebook.stetho.Stetho;

import alex.com.darkskyapp.components.app.dagger.AppComponent;
import alex.com.darkskyapp.components.app.dagger.AppModule;
import alex.com.darkskyapp.components.app.dagger.DaggerAppComponent;
import alex.com.darkskyapp.components.forecast.dagger.DaggerForecastServiceComponent;
import alex.com.darkskyapp.components.forecast.dagger.ForecastServiceComponent;
import alex.com.darkskyapp.components.forecast.dagger.ForecastServiceModule;
import alex.com.darkskyapp.components.app.dagger.NetworkModule;
import alex.com.darkskyapp.components.app.dagger.UserDataModule;
import timber.log.Timber;

/**
 * Created by Alex on 11/11/2017.
 */

public class DarkSkyApp extends Application {

    private static AppComponent _AppComponent;
    private static ForecastServiceComponent _ForecastServiceComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        _AppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .userDataModule(new UserDataModule()).build();

        Timber.plant(new Timber.DebugTree());
        Stetho.initializeWithDefaults(this);
    }

    public static AppComponent getAppComponent() {
        return _AppComponent;
    }


    public static ForecastServiceComponent getForecastServiceComponent() {
        if (_ForecastServiceComponent == null) {
            _ForecastServiceComponent = DaggerForecastServiceComponent.builder()
                    .appComponent(getAppComponent())
                    .forecastServiceModule(new ForecastServiceModule()).build();
        }
        return _ForecastServiceComponent;
    }
}