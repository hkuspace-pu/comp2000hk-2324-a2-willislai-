package comp2000hk.cw2.seasiderestaurant.ui.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import comp2000hk.cw2.seasiderestaurant.databinding.FragmentHomeBinding;
import comp2000hk.cw2.seasiderestaurant.ui.booking.RecycleViewAdapter;
import comp2000hk.cw2.seasiderestaurant.ui.booking.ReservationHist;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private RecyclerView recyclerViewHome;

    private List<ImageURL> lstHomeFoodImg = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerViewHome = binding.rvHome;

        // Clear the list before adding items
        lstHomeFoodImg.clear();

        ImageURL img1 = new ImageURL();
        img1.setImage_url("https://drive.google.com/uc?id=1sFOX2V6J8B36z2IJXQu-5tam1oICRdsU");
        lstHomeFoodImg.add(img1);
        ImageURL img2 = new ImageURL();
        img2.setImage_url("https://drive.google.com/uc?id=19DBCUJDQ7HCsx8My7fy5tVHXTBUH12k0");
        lstHomeFoodImg.add(img2);
        ImageURL img3 = new ImageURL();
        img3.setImage_url("https://drive.google.com/uc?id=15FRCDhI6Bt4UetZDmwsxcvekb05XeRvi");
        lstHomeFoodImg.add(img3);
        ImageURL img4 = new ImageURL();
        img4.setImage_url("https://drive.google.com/uc?id=10fFqZqC6DkC0MAiI1_Yj30sdEO0zZ3ee");
        lstHomeFoodImg.add(img4);
        ImageURL img5 = new ImageURL();
        img5.setImage_url("https://drive.google.com/uc?id=10gLWKoBkBepH372D4Xk_HEXYTsqtHlEX");
        lstHomeFoodImg.add(img5);
        ImageURL img6 = new ImageURL();
        img6.setImage_url("https://drive.google.com/uc?id=1_GC9gTafM-h4296VZDMTv8T-FxCpMElv");
        lstHomeFoodImg.add(img6);
        ImageURL img7 = new ImageURL();
        img7.setImage_url("https://drive.google.com/uc?id=1-zNDpHhhMuUAQBQfUvPnX5qZ4o7HUAaS");
        lstHomeFoodImg.add(img7);
        ImageURL img8 = new ImageURL();
        img8.setImage_url("https://drive.google.com/uc?id=1CuaD4iSYSXSQ28AqAAvulr3DcyslCC-T");
        lstHomeFoodImg.add(img8);
        ImageURL img9 = new ImageURL();
        img9.setImage_url("https://drive.google.com/uc?id=1VFzC_GjFYRDEALx-9I6CNIyegA26LTOL");
        lstHomeFoodImg.add(img9);
        ImageURL img10 = new ImageURL();
        img10.setImage_url("https://drive.google.com/uc?id=1H89zkyBP6Le2jTPLVd1xvlHe-UFMEhmX");
        lstHomeFoodImg.add(img10);

        SetupRecyclerViewHome(lstHomeFoodImg);

        return root;
    }

    public void SetupRecyclerViewHome (List<ImageURL> lstFoodImg) {

        HomeRecyclerViewAdapter myAdapter = new HomeRecyclerViewAdapter(requireContext(), lstFoodImg) ;
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewHome.setAdapter(myAdapter);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}