package comp2000hk.cw2.seasiderestaurant.ui.booking;

import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import comp2000hk.cw2.seasiderestaurant.AsyncTaskCallback;


public class ApiRequestAsyncTask extends AsyncTask<Void, Void, JSONArray> {
    private String apiUrl;
    private AsyncTaskCallback callback;
    private JSONArray result;

    public ApiRequestAsyncTask(String apiUrl, AsyncTaskCallback callback) {
        this.apiUrl = apiUrl;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        // Make the API request using Volley here
        // Volley's JsonObjectRequest or JsonArrayRequest

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Handle the response
                        result = response;
                        callback.onTaskComplete(String.valueOf(result));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        result = null;
                        callback.onTaskComplete(result);
                    }
                });

        // Add the request to the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(/*context*/);
        requestQueue.add(jsonArrayRequest);

        return null;
    }

    @Override
    protected void onPostExecute(JSONArray result) {
        // This method is called on the main thread after doInBackground completes
        // Handle the API response in the UI
        super.onPostExecute(result);

        // Pass the result to the callback
        if (callback != null) {
            callback.onTaskComplete(String.valueOf(result));
        }
    }
}

