package com.droid.manas.currencyconverter.conversionscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.manas.currencyconverter.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConversionActivity extends AppCompatActivity implements ConversionScreenContract.PresenterViewContract {

    private static final String FLAG_FORMAT = "http://www.geonames.org/flags/x/%s.gif";
    private static final String CONVERTED_CURRENCY_FORMAT = "Converted Currency (%s)";

    @BindView(R.id.edt_amount)
    EditText edtAmount;

    @BindView(R.id.tv_amount)
    TextView tvAmount;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_from_currency)
    TextView tvFromCurrency;

    @BindView(R.id.tv_to_currency)
    TextView tvToCurrency;

    @BindView(R.id.tv_converted_currency)
    TextView tvConvertedCurrency;

    @BindView(R.id.tv_to_rate)
    TextView tvToRate;

    @BindView(R.id.tv_from_rate)
    TextView tvFromRate;

    @BindView(R.id.iv_from_flag)
    ImageView ivFromFlag;

    @BindView(R.id.iv_to_flag)
    ImageView ivToFlag;

    ConversionScreenContract.ViewPresenterContract viewPresenterContract;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_conversion);
        ButterKnife.bind(this);

        initializeToolbar();

        viewPresenterContract = new ConversionPresenter(this);

        edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!TextUtils.isEmpty(s.toString().trim())) {
                    viewPresenterContract.onAmountChanged(Float.parseFloat(s.toString()));
                } else {
                    onAmountConverted(0f);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initializeToolbar() {
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("");
        }
    }

    @Override
    public void onFromCurrencyChanged(String code) {
        tvFromCurrency.setText(code);

        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(ivFromFlag);
        Glide.with(this).load(String.format(FLAG_FORMAT,code.substring(0, 2).toLowerCase())).into(imageViewTarget);
    }

    @Override
    public void onToCurrencyChanged(String code) {
        tvToCurrency.setText(code);
        tvConvertedCurrency.setText(String.format(CONVERTED_CURRENCY_FORMAT, code));

        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(ivToFlag);
        Glide.with(this).load(String.format(FLAG_FORMAT,code.substring(0, 2).toLowerCase())).into(imageViewTarget);
    }

    @Override
    public void onAmountConverted(float concertedAmount) {
        tvAmount.setText(String.valueOf(concertedAmount));
    }

    @Override
    public void setUnitRateFrom(String unitRateFrom) {
        tvFromRate.setText(unitRateFrom);
    }

    @Override
    public void setUnitRateTo(String string) {
        tvToRate.setText(string);
    }

    @OnClick({R.id.ll_from, R.id.ll_to})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_from:
                new CurrencyPickerSheet(this, viewPresenterContract, 0).show();
                break;
            case R.id.ll_to:
                new CurrencyPickerSheet(this, viewPresenterContract, 1).show();
                break;
        }

    }
}
