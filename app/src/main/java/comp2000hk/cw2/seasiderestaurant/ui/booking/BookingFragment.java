package comp2000hk.cw2.seasiderestaurant.ui.booking;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import comp2000hk.cw2.seasiderestaurant.AsyncTaskCallback;
import comp2000hk.cw2.seasiderestaurant.R;
import comp2000hk.cw2.seasiderestaurant.databinding.FragmentBookingBinding;

public class BookingFragment extends Fragment {

    private String URL_JSON = "";
    private RequestQueue requestQueue;
    private JsonArrayRequest ArrayRequest;

    private List<ReservationHist> lstReservationHist = new ArrayList<>();
    private RecyclerView recyclerViewBookingHist;

    private FragmentBookingBinding binding;
    //private TextView textView;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BookingViewModel bookingViewModel =
                new ViewModelProvider(this).get(BookingViewModel.class);

        binding = FragmentBookingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Button btnGotoAddBooking = binding.btnAddBookingInHist;

        //btnGotoAddBooking.setOnClickListener((new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Navigation.findNavController(v).navigate(R.id.navigation_addBooking);

        //    }
        //}));

        recyclerViewBookingHist = binding.rv;

        jsonCallBookingHist();

        return root;
    }

    public void SetupRecyclerView (List<ReservationHist> lstBookingHist) {

        RecycleViewAdapter myAdapter = new RecycleViewAdapter(requireContext(), lstBookingHist) ;
        recyclerViewBookingHist.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewBookingHist.setAdapter(myAdapter);

    }

    private void jsonCallBookingHist() {
        URL_JSON = getString(R.string.JSON_API_URL);

        new ApiRequestAsyncTask(URL_JSON, new AsyncTaskCallback() {
            @Override
            public void onTaskComplete(JSONArray result) {
                if (result != null) {
                    handleJsonResponse(result);
                } else {
                    // Handle null or error case
                    Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
                }
            }
        }).execute();
    }

    private void handleJsonResponse(JSONArray response) {
        URL_JSON = getString(R.string.JSON_API_URL);

        // Clear the existing list before populating it with new data
        lstReservationHist.clear();

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, URL_JSON, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSONObject jsonObject = null;

                        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                jsonObject = response.getJSONObject(i);

                                String customerName = jsonObject.getString("customerName");

                                if (customerName.endsWith(userEmail)) {
                                    ReservationHist reservationHist = new ReservationHist();

                                    reservationHist.setId(jsonObject.getInt("id"));
                                    reservationHist.setName(jsonObject.getString("customerName"));
                                    reservationHist.setPhoneNo(jsonObject.getString("customerPhoneNumber"));
                                    reservationHist.setMeal(jsonObject.getString("meal"));
                                    reservationHist.setArea(jsonObject.getString("seatingArea"));
                                    reservationHist.setTableSize(jsonObject.getInt("tableSize"));
                                    reservationHist.setDate(jsonObject.getString("date"));
                                    lstReservationHist.add(reservationHist);

                                    //int id = jsonObject.getInt("id");
                                    //String name = jsonObject.getString("customerName");
                                    //String phoneNo = jsonObject.getString("customerPhoneNumber");
                                    //String meal = jsonObject.getString("meal");
                                    //String area = jsonObject.getString("seatingArea");
                                    //int size = jsonObject.getInt("tableSize");
                                    //String date = jsonObject.getString("date");
                                    //textView.append(String.valueOf(id)+ ", " + name + ", " + phoneNo + ", " +
                                    //        meal + ", " + area + ", " + String.valueOf(size) + ", " + date + "\n\n");
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // Check if lstReservationHist is empty
                        if (lstReservationHist.isEmpty()) {
                            // Display a message to the user
                            TextView noBookingMessage = binding.textViewNoBooking;
                            noBookingMessage.setVisibility(View.VISIBLE); // Make the TextView visible
                        } else {
                            // If there are booking records, proceed to set up the RecyclerView
                            TextView noBookingMessage = binding.textViewNoBooking;
                            noBookingMessage.setVisibility(View.INVISIBLE); // Make the TextView visible
                            SetupRecyclerView(lstReservationHist);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(arrayRequest);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
