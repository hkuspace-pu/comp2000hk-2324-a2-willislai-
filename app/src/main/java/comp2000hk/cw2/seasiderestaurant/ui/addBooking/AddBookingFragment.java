package comp2000hk.cw2.seasiderestaurant.ui.addBooking;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import comp2000hk.cw2.seasiderestaurant.R;
import comp2000hk.cw2.seasiderestaurant.databinding.FragmentAddBookingBinding;

public class AddBookingFragment extends Fragment {

    private FragmentAddBookingBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        AddBookingViewModel addBookingViewModel =
                new ViewModelProvider(this).get(AddBookingViewModel.class);

        binding = FragmentAddBookingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAddBooking;
        EditText etPhoneNo = binding.inputPhoneNumber;
        Spinner spinnerMeal = binding.inputMeal;
        Spinner spinnerSeatingArea = binding.inputSeatingArea;
        Spinner spinnerTableSize = binding.inputTableSize;
        EditText etReserveDate = binding.inputDate;

        // Create ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> mealAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.meal_array,
                R.layout.spinner_dropdown_item
        );
        mealAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMeal.setAdapter(mealAdapter);

        //spinnerMeal.setOnItemSelectedListener(listener);

        ArrayAdapter<CharSequence> seatingAreaAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.seating_area_array,
                R.layout.spinner_dropdown_item
        );
        seatingAreaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeatingArea.setAdapter(seatingAreaAdapter);

        ArrayAdapter<CharSequence> tableSizeAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.table_size_array,
                R.layout.spinner_dropdown_item
        );
        tableSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTableSize.setAdapter(tableSizeAdapter);

        addBookingViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        addBookingViewModel.getPhoneNumber().observe(getViewLifecycleOwner(), value -> {
            // Update UI or perform any necessary action
        });

        addBookingViewModel.getMealPeriod().observe(getViewLifecycleOwner(), value -> {
            // Update UI or perform any necessary action
        });

        addBookingViewModel.getSeatingArea().observe(getViewLifecycleOwner(), value -> {
            // Update UI or perform any necessary action
        });

        addBookingViewModel.getTableSize().observe(getViewLifecycleOwner(), value -> {
            // Update UI or perform any necessary action
        });

        addBookingViewModel.getReserveDate().observe(getViewLifecycleOwner(), value -> {
            // Update UI or perform any necessary action
        });


        binding.btnAddBooking.setOnClickListener(v -> {

            String sPhotoNo = etPhoneNo.getText().toString();
            String sMealPeriod = spinnerMeal.getSelectedItem().toString();
            String sSeatingArea = spinnerSeatingArea.getSelectedItem().toString();
            String sTableSize = spinnerTableSize.getSelectedItem().toString();
            String sReserveDate = etReserveDate.getText().toString();

            String username = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

            Reservation reservation = this.createReservation(username, sPhotoNo, sMealPeriod, sSeatingArea, sTableSize, sReserveDate);

            // Call API with reservation data
            sendReservationToApi(reservation);
        });


        return root;
    }

    private AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // Set text color to black for the selected item
            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Set text color to grey when nothing is selected
            ((TextView) parent.getChildAt(0)).setTextColor(Color.GRAY);
        }
    };

    // Create a method to convert input values into a Reservation object
    public Reservation createReservation(String name, String phoneNo, String meal, String area, String size, String date) {

        // Check if tableSizeValue is not empty before parsing to avoid NumberFormatException
        int parsedTableSize = size.isEmpty() ? 0 : Integer.parseInt(size);

        return new Reservation(
                name,
                phoneNo,
                meal,
                area,
                parsedTableSize,
                date
        );
    }

    private void sendReservationToApi(Reservation reservation) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(requireContext());

        // Get the current logged-in user's email
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        // Append the user's email to the customerName in the reservation
        reservation.setName(reservation.getName() + " " + userEmail);

        // Define the API endpoint URL
        String apiUrl = "https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/Reservations";

        // Create a StringRequest with POST method
        StringRequest stringRequest = new StringRequest(Request.Method.POST, apiUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle successful response
                        showBookingSuccess();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        // Print or log the error message for debugging
                        if (error.networkResponse != null && error.networkResponse.data != null) {
                            String errorMessage = new String(error.networkResponse.data);
                            System.out.println("Error response: " + errorMessage);
                        }

                        showBookingFail();
                    }
                }) {
            @Override
            public byte[] getBody() {
                try {
                    // Create a JSON object representing the reservation data
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("customerName", reservation.getName());
                    jsonBody.put("customerPhoneNumber", reservation.getPhoneNo());
                    jsonBody.put("meal", reservation.getMeal());
                    jsonBody.put("seatingArea", reservation.getArea());
                    jsonBody.put("tableSize", reservation.getSize());
                    jsonBody.put("date", reservation.getDate());

                    // use the following data for testing the API
                    //jsonBody.put("customerName", "mary job");
                    //jsonBody.put("customerPhoneNumber", "22334455");
                    //jsonBody.put("meal", "lunch");
                    //jsonBody.put("seatingArea", "inside");
                    //jsonBody.put("tableSize", 2);
                    //jsonBody.put("date", "2024-03-03");

                    // Convert the JSON object to a string and get the bytes
                    String jsonString = jsonBody.toString();
                    return jsonString.getBytes("utf-8");
                } catch (JSONException | UnsupportedEncodingException e) {
                    // Handle the exception (e.g., log an error)
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    private String convertReservationToJson(Reservation reservation) {
        Gson gson = new Gson();
        return gson.toJson(reservation);
    }

    private void showBookingSuccess() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Booking")
                .setMessage("successful")
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showBookingFail() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Booking")
                .setMessage("Fail")
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
