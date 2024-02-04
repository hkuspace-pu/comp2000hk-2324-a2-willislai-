package comp2000hk.cw2.seasiderestaurant.ui.addBooking;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
        TextView etSelectDate = binding.inputDate;

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

        etSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        requireContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                etSelectDate.setText(year + "-" + String.format("%02d", monthOfYear + 1)
                                        + "-" + String.format("%02d", dayOfMonth));

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

        binding.btnAddBooking.setOnClickListener(v -> {

            String sPhoneNo = etPhoneNo.getText().toString();
            String sMealPeriod = spinnerMeal.getSelectedItem().toString();
            String sSeatingArea = spinnerSeatingArea.getSelectedItem().toString();
            String sTableSize = spinnerTableSize.getSelectedItem().toString();
            String sReserveDate = etSelectDate.getText().toString();

            // Check if sPhoneNo is empty
            if (TextUtils.isEmpty(sPhoneNo)) {
                // If sPhoneNo is empty, show an AlertDialog indicating that the phone number is required
                showAlertDialog("Invalid Input", "Please enter your phone number");
                return;
            }
            // Check if sMealPeriod is valid
            if (!(sMealPeriod.equals("Breakfast") || sMealPeriod.equals("Lunch") || sMealPeriod.equals("Tea") || sMealPeriod.equals("Dinner"))) {
                showAlertDialog("Invalid Input", "Please enter a valid Meal Period (Breakfast, Lunch, Tea, or Dinner)");
                return;
            }

            // Check if sSeatingArea is valid
            if (!(sSeatingArea.equals("Inside") || sSeatingArea.equals("Outside") || sSeatingArea.equals("Seaside") || sSeatingArea.equals("Garden side"))) {
                showAlertDialog("Invalid Input", "Please enter a valid Seating Area (Inside, Outside, Seaside, or Garden side)");
                return;
            }

            // Check if sTableSize is a valid number between 1 and 8
            try {
                int tableSize = Integer.parseInt(sTableSize);
                if (tableSize < 1 || tableSize > 10) {
                    showAlertDialog("Invalid Input", "Please enter a valid Table Size (between 1 and 10)");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlertDialog("Invalid Input", "Please enter a valid Table Size (numeric value between 1 and 10)");
                return;
            }

            // Check if sReserveDate is a valid date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            try {
                // Set lenient to false to enforce strict date parsing
                dateFormat.setLenient(false);
                Date parsedDate = dateFormat.parse(sReserveDate);

                // If parsing is successful, the date is in the correct format
                // Continue with further processing
            } catch (ParseException e) {
                // If parsing fails, show an AlertDialog indicating an invalid date format
                showAlertDialog("Invalid Input", "Please enter a valid date in the format yyyy-MM-dd");
                return;
            }

            String username = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

            Reservation reservation = this.createReservation(username, sPhoneNo, sMealPeriod, sSeatingArea, sTableSize, sReserveDate);

            // Call API with reservation data
            sendReservationToApi(reservation);
            // After a successful API call, reset the input elements
            etPhoneNo.setText(getString(R.string.your_phoneno));
            spinnerMeal.setSelection(0);
            spinnerSeatingArea.setSelection(0);
            spinnerTableSize.setSelection(0);
            etSelectDate.setText(getString(R.string.desired_date));
        });

        return root;
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
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
        String apiUrl = getString(R.string.JSON_API_URL);

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
