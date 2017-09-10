package com.xjcrepe.sgpsi.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xjcrepe.sgpsi.R;
import com.xjcrepe.sgpsi.model.PsiReadings;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View, AdapterView.OnItemSelectedListener {

    private static final String KEY_CURRENT_READING_TYPE = "key_reading_type";

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

    @BindView(R.id.ivSgMap)
    ImageView ivSgMap;

    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();
        presenter.bindView(this);

        // default to first element in the list
        int readingType = 0;
        if (savedInstanceState != null) {
            readingType = savedInstanceState.getInt(KEY_CURRENT_READING_TYPE);
        }
        spinner.setSelection(readingType);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_CURRENT_READING_TYPE, spinner.getSelectedItemPosition());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        presenter.unbindView();
        super.onDestroy();
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
        Snackbar.make(spinner, errorMsg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        presenter.fetchPsiReadingsOfType(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //No-op
    }

    private void initViews() {
        Glide.with(this).load(R.drawable.img_sg_map).fitCenter().into(ivSgMap);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.psi_readings_types, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
    }
}
