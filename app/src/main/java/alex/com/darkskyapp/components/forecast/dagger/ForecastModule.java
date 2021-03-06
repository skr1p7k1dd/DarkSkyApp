package alex.com.darkskyapp.components.forecast.dagger;

import alex.com.darkskyapp.components.app.api.APIClient;
import alex.com.darkskyapp.components.app.data.LocationManager;
import alex.com.darkskyapp.components.forecast.core.ForecastModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex on 11/11/2017.
 */

@Module
public class ForecastModule {

    @Provides
    @ForecastScope
    ForecastModel provideModel(APIClient apiClient, LocationManager locationManager) {
        return new ForecastModel(apiClient, locationManager);
    }

}
