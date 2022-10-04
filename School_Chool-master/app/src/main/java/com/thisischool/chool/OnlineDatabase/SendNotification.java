package com.thisischool.chool.OnlineDatabase;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.thisischool.chool.Classes.Constants.CLICK_ACTION;
import static com.thisischool.chool.Classes.Constants.TO_FRIEND_REQUEST;

public class SendNotification {

    private static final String TAG = "SendNotification";
    private static final String mFirebaseServerKey = "some key :)";
    private static final String URL = "https://fcm.googleapis.com/fcm/send";

    public static void messageNotification (Context context, String Nickname,
                                    String Message, String DeviceToken) {
        RequestQueue mRequestQue = Volley.newRequestQueue(context);
        try {
            json.put("to", DeviceToken);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    json,
                    response -> {
                        Log.d(TAG, "messageNotification: ");
                        },
                    error -> Log.d(TAG, "onError messageNotification: " + error.networkResponse)
            ) {
                    return header;
                }
            };
            mRequestQue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static void classMessageNotification (Context context, String Nickname,
                                            String Message, String ClassId) {
        RequestQueue mRequestQue = Volley.newRequestQueue(context);
        String body = Nickname + ": " + Message;

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    json,
                    response -> {
                        Log.d(TAG, "messageNotification: ");
                    },
                    error -> Log.d(TAG, "onError messageNotification: " + error.networkResponse)
            ) {
                @NotNull
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> header = new HashMap<>();
                    header.put("Content-Type", "application/json");
                    header.put("Authorization", mFirebaseServerKey);
                    return header;
                }
            };
            mRequestQue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void schoolMessageNotification (Context context, String Nickname,
                                                 String Message, String ClassId) {
        RequestQueue mRequestQue = Volley.newRequestQueue(context);
        String body = Nickname + ": " + Message;
        JSONObject json = new JSONObject();
        try {
            json.put("to/topic/", ClassId);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    json,
                    response -> {
                        Log.d(TAG, "messageNotification: ");
                    },
                    error -> Log.d(TAG, "onError messageNotification: " + error.networkResponse)
            ) {
                @NotNull
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> header = new HashMap<>();
                    header.put("Content-Type", "application/json");
                    header.put("Authorization", mFirebaseServerKey);
                    return header;
                }
            };
            mRequestQue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void likeNotification (Context context, String Nickname,
                                         String DeviceToken) {
        RequestQueue mRequestQue = Volley.newRequestQueue(context);
        String body = Nickname + " liked your message!";
        JSONObject json = new JSONObject();");
            notificationObj.put("body", body);
            json.put("notification", notificationObj);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    json,
                    response -> {
                        Log.d(TAG, "likeNotification: ");
                    },
                    error -> Log.d(TAG, "onError likeNotification: "
                    header.put("Authorization", mFirebaseServerKey);
                    return header;
                }
            };
            mRequestQue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void friendRequestNotification (Context context, Strin
        String body = Nickname + " sent you a Friend Request!";
        JSONObject json = new JSONObject();
        try {
            json.put("to", DeviceToken);
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title", "New Friend Request");
            notificationObj.put("body", body);
            notificationObj.put(CLICK_ACTION, TO_FRIEND_REQUEST);
            json.put("notification", notificationObj);
                    header.put("Authorization", mFirebaseServerKey);
                    return header;
                }
            };
            mRequestQue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void friendRequestAcceptedNotification (Context context, String Nickname,
                                                  String DeviceToken) {
        RequestQueue mRequestQue = Volley.newRequestQueue(context);
        String body = Nickname + " accepted you a Friend Request!";
        JSONObject json = new JSONObject();
        try {
            json.put("to", DeviceToken);
            JSONObject notificationObj = new JSONObREQUEST);
            json.put("notification", notificationObj);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    json,
                    response -> {
                        Log.d(TAG, "friendRequestNotification: ");                    },
                    error -> Log.d(TAG, "onError friendRequestNotification: " + error.networkResponse)
            ) {
                @NotN
            };
            mRequestQue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
