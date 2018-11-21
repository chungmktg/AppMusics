package com.android.chungpro.appmusics.Service;

public class APIService {
    private static String base_url = "https://tranthienchung.000webhostapp.com/Server/";
    private static String base_url1="https://chungtranthien.000webhostapp.com/Server/";
    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);

    }
    public static DataService getService1(){
        return APIRetrofitClient.getClient(base_url1).create(DataService.class);

    }
}
