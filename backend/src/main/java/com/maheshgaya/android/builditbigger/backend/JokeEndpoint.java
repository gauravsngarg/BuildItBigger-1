/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.maheshgaya.android.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.NotFoundException;
import com.maheshgaya.JokeTeller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokesApi",
        version = "v1",
        description = "An API for jokes",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.android.maheshgaya.com",
                ownerName = "backend.builditbigger.android.maheshgaya.com",
                packagePath = ""
        )
)
public class JokeEndpoint {
    private static final Logger log = Logger.getLogger(JokeEndpoint.class.getName());

    @ApiMethod(name = "getJoke")
    public JokeTeller getJoke(@Named("joke") String newJoke) throws NotFoundException{
        if (newJoke != null) {
            return new JokeTeller(newJoke);
        } else {
            return new JokeTeller();
        }

    }


}
