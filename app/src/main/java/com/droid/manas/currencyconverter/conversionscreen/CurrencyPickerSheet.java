package com.droid.manas.currencyconverter.conversionscreen;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.droid.manas.currencyconverter.model.CurrencyNames;
import com.example.manas.currencyconverter.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link BottomSheetDialog} for currency pick
 */
public class CurrencyPickerSheet extends BottomSheetDialog implements CurrencyClickListener {

    @BindView(R.id.rv_currency)
    RecyclerView rvCurrency;

    private ConversionScreenContract.ViewPresenterContract view;
    private int requestType;

    public CurrencyPickerSheet(Context context, ConversionScreenContract.ViewPresenterContract viewPresenterContract, int i) {
        super(context);
        view = viewPresenterContract;
        requestType = i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_picker_sheets);
        ButterKnife.bind(this);

        rvCurrency.setLayoutManager(new LinearLayoutManager(getContext()));

        rvCurrency.setAdapter(new CurrencyAdapter(getContext(), view.getLoadedCurrencies(), this));
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void onCurrencyClicked(CurrencyNames currencyNames) {

        if (requestType == 0)
            view.setFromCurrency(currencyNames.getCurrencyAbbreviation());
        else if (requestType == 1)
            view.setToCurrency(currencyNames.getCurrencyAbbreviation());

        this.dismiss();
    }
}
