package com.example.android3_lesson2_retrofit.ui.films;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3_lesson2_retrofit.R;
import com.example.android3_lesson2_retrofit.databinding.FragmentScanBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class ScanFragment extends Fragment {

    private FragmentScanBinding ui;
    private IntentIntegrator intentIntegrator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ui = FragmentScanBinding.inflate(inflater);
        return ui.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intentIntegrator = new IntentIntegrator(requireActivity());

        ui.btnReadQr.setOnClickListener(v -> {
            intentIntegrator.initiateScan();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String value = result.getContents();
        ui.etValue.setText(value);

        Bundle bundle = new Bundle();
        bundle.putString(FilmsFragment.KEY_FILM, ui.etValue.getText().toString());
        FilmDetailsFragment detailsFragment = new FilmDetailsFragment();
        detailsFragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, detailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}