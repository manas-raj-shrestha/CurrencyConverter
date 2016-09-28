package com.droid.manas.currencyconverter.conversionscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.droid.manas.currencyconverter.model.CurrencyNames;
import com.example.manas.currencyconverter.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrenciesViewHolder> {

    private static final String FLAG_FORMAT = "http://www.geonames.org/flags/x/%s.gif";

    private Context context;
    private RealmResults<CurrencyNames> loadedCurrencies;
    private CurrencyClickListener currencyClickListener;

    public CurrencyAdapter(Context context, RealmResults<CurrencyNames> loadedCurrencies, CurrencyClickListener currencyClickListener) {
        this.context = context;
        this.loadedCurrencies = loadedCurrencies;
        this.currencyClickListener = currencyClickListener;
    }

    @Override
    public CurrenciesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_currency_items, parent, false);
        return new CurrenciesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CurrenciesViewHolder holder, int position) {
        final CurrencyNames currencyNames = loadedCurrencies.get(position);
        holder.tvCurrencyInfo.setText(currencyNames.getCurrencyAbbreviation() + "\n" + currencyNames.getCurrencyFullName());

        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(holder.ivFlag);
        Glide.with(context)
                .load(String.format(FLAG_FORMAT,currencyNames.getCurrencyAbbreviation().substring(0, 2).toLowerCase()))
                .skipMemoryCache(false)
                .into(imageViewTarget);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currencyClickListener.onCurrencyClicked(currencyNames);
            }
        });
    }

    @Override
    public int getItemCount() {
        return loadedCurrencies.size();
    }

    public class CurrenciesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_currency_info)
        TextView tvCurrencyInfo;

        @BindView(R.id.iv_flag)
        ImageView ivFlag;

        public CurrenciesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
