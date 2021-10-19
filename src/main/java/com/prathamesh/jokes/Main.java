package com.prathamesh.jokes;



import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;

public class Main {

    private final OkHttpClient okHttpClient = new OkHttpClient();

    public static void main(String[] args) throws Exception {
	// write your code here

        Main main = new Main();
        main.sendGet();

    }

  

    private void sendGet() throws Exception{
        Request request = new Request.Builder()
                .url("https://v2.jokeapi.dev/joke/Any?amount=10")
                .build();

        try (Response response = okHttpClient.newCall(request).execute()){

            if(!response.isSuccessful()) throw new IOException("Failed to get response..!!"+response);

            String res = response.body().string();
            System.out.println(res);
//            JSONObject jsonObject = new JSONObject(res);


//            if(jsonObject.getString("type").equals("twopart")){
//                System.out.println(jsonObject.getString("setup")+"\n"+jsonObject.getString("delivery"));
//            }
//            else {
//
//                String joke = jsonObject.getString("joke");
//                System.out.println(joke);
//
//            }
            JSONObject jo = new JSONObject(res);

            JSONArray jsonArray = jo.getJSONArray("jokes");

            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if(jsonObject.getString("type").equals("twopart")){
                System.out.println(jsonObject.getString("setup")+"\n"+jsonObject.getString("delivery"));
                    System.out.println("\n\n"+" Joke Category :"+jsonObject.getString("category"));
                }
                else {

                    String joke = jsonObject.getString("joke");
                    System.out.println(joke);
                    System.out.println("\n\n"+" Joke Category :"+jsonObject.getString("category"));

                }

                System.out.println("-------------------------------------------------------------------------------------");

            }





        }
    }
}
