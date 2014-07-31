package com.github.goive.steamapi.client;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.github.goive.steamapi.exceptions.InvalidAppIdException;
import com.github.goive.steamapi.exceptions.SteamApiException;
import com.github.goive.steamapi.utils.ResultMapUtils;

public class SteamApiClient {

    private String apiUrl = "http://store.steampowered.com/api/appdetails?appids=";

    public SteamApiClient() {

    }

    public SteamApiClient(String customApiUrl) {
        this.apiUrl = customApiUrl;
    }

    @SuppressWarnings("unchecked")
    public Map<Object, Object> retrieveResultBodyMapForId(long appId) throws SteamApiException {
        Map<Object, Object> resultMap = new HashMap<Object, Object>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            URL src = new URL(apiUrl + appId);
            resultMap = mapper.readValue(src, Map.class);
        } catch (IOException e) {
            throw new SteamApiException(e);
        }

        if (!ResultMapUtils.isSuccessfullyRetrieved(resultMap)) {
            throw new InvalidAppIdException(appId);
        }

        return resultMap;
    }

}