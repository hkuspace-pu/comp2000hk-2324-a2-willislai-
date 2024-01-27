package comp2000hk.cw2.seasiderestaurant.ui.addBooking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import comp2000hk.cw2.seasiderestaurant.databinding.FragmentAddBookingBinding;

public class AddBookingFragment extends Fragment {

    private FragmentAddBookingBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddBookingViewModel homeViewModel =
                new ViewModelProvider(this).get(AddBookingViewModel.class);

        binding = FragmentAddBookingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAddBooking;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}