package com.xjcrepe.sgpsi.map;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.xjcrepe.sgpsi.R;
import com.xjcrepe.sgpsi.model.PsiReadings;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View, AdapterView.OnItemSelectedListener{

    @BindView(R.id.tvNorth)
    TextView tvNorth;

    @BindView(R.id.tvSouth)
    TextView tvSouth;

    @BindView(R.id.tvEast)
    TextView tvEast;

    @BindView(R.id.tvWest)
    TextView tvWest;

    @BindView(R.id.tvCentral)
    TextView tvCentral;

    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showPsiReadings(@NonNull PsiReadings psiReadings) {
        tvEast.setText(String.valueOf(psiReadings.getEast()));
        tvWest.setText(String.valueOf(psiReadings.getWest()));
        tvNorth.setText(String.valueOf(psiReadings.getNorth()));
        tvSouth.setText(String.valueOf(psiReadings.getSouth()));
        tvCentral.setText(String.valueOf(psiReadings.getCentral()));
    }

    @Override
    public void showErrorMessage(String errorMsg) {
        Snackbar.make(spinner, errorMsg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void initViews() {
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.psi_readings_types, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
    }
}
