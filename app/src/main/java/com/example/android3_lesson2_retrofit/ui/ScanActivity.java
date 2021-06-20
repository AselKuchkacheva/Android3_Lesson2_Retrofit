package com.example.android3_lesson2_retrofit.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.android3_lesson2_retrofit.R;
import com.example.android3_lesson2_retrofit.databinding.ActivityScanBinding;
import com.example.android3_lesson2_retrofit.ui.films.FilmsFragment;
import com.example.android3_lesson2_retrofit.ui.films.ScanFragment;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class ScanActivity extends AppCompatActivity {

    private ActivityScanBinding ui;
    private IntentIntegrator intentIntegrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityScanBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        intentIntegrator = new IntentIntegrator(this);

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
        ScanFragment scanFragment = new ScanFragment();
        scanFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, scanFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}