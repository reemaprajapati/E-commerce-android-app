package com.example.otimus.ecommerceapp.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.otimus.ecommerceapp.R;


public class QuantityCounter extends LinearLayout {
    private static final int MAX_ALLOWED_PRODUCT_QUANTITY = 9999;
    private static final int MIN_ALLOWED_PRODUCT_QUANTITY = 1;
    private static final int QUANTITY_NONE = 0;
    private TextView tvNumber;
    private QuantityChangeListener listener;
    private int currentQuantity;

    public QuantityCounter(Context context) {
        this(context, null);
    }

    public QuantityCounter(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuantityCounter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public QuantityCounter(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_view_quantity_counter, this, true);
        Button btnInc = (Button) findViewById(R.id.btnInc);
        Button btnDec = (Button) findViewById(R.id.btnDec);
        tvNumber = (TextView) findViewById(R.id.etQuantity);
        setQuantity(MIN_ALLOWED_PRODUCT_QUANTITY);

        btnInc.setOnClickListener(v -> {
            int quantity = getEnteredQuantity();
            if (quantity > QUANTITY_NONE && quantity < MAX_ALLOWED_PRODUCT_QUANTITY) {
                quantity++;
                setQuantity(quantity, true);
            } else if (quantity == MAX_ALLOWED_PRODUCT_QUANTITY) {
                Toast.makeText(v.getContext(), R.string.max_product_quantity_limit_reached, Toast.LENGTH_SHORT).show();
            } else {
                setQuantity(MIN_ALLOWED_PRODUCT_QUANTITY, true);
            }
        });

        btnDec.setOnClickListener(v -> {
            int quantity = getEnteredQuantity();
            if (quantity > MIN_ALLOWED_PRODUCT_QUANTITY) {
                quantity--;
                setQuantity(quantity, true);
            } else {
                setQuantity(MIN_ALLOWED_PRODUCT_QUANTITY, true);
            }
        });
    }

    private void setQuantity(int quantity, boolean notify) {
        setQuantity(quantity);
        if (notify && listener != null)
            listener.onQuantityChanged(quantity);
    }

    public void setQuantity(int quantity) {
        if (quantity == currentQuantity) return;
        if (quantity >= MIN_ALLOWED_PRODUCT_QUANTITY && quantity <= MAX_ALLOWED_PRODUCT_QUANTITY) {
            currentQuantity = quantity;
            tvNumber.setText(String.valueOf(currentQuantity));
        } else
            throw new IllegalArgumentException("invalid quantity range");
    }

    public int getEnteredQuantity() {
        Integer number;
        try {
            number = Integer.valueOf(tvNumber.getText().toString().trim());
            if (number < MIN_ALLOWED_PRODUCT_QUANTITY && number > MAX_ALLOWED_PRODUCT_QUANTITY) {
                number = QUANTITY_NONE;
            }
        } catch (NumberFormatException enteredNumber) {
            number = QUANTITY_NONE;
        }
        return number;
    }

    public void setQuantityChangeListener(QuantityChangeListener listener) {
        this.listener = listener;
    }

    public interface QuantityChangeListener {
        void onQuantityChanged(int newQuantity);
    }
}
