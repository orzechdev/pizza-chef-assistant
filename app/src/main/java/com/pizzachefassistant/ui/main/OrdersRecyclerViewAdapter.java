package com.pizzachefassistant.ui.main;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pizzachefassistant.BR;
import com.pizzachefassistant.R;
import com.pizzachefassistant.repository.model.Order;

import java.util.List;

public class OrdersRecyclerViewAdapter extends RecyclerView.Adapter<OrdersRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "OrdersRecyclerViewAd";
    public List<Order> orders;
    public OrdersViewModel viewModel;

    public OrdersRecyclerViewAdapter(List<Order> orders, OrdersViewModel viewModel) {
        this.orders = orders;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public OrdersRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.order_card, viewGroup, false);
        return new OrdersRecyclerViewAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called.");
        final Order order = orders.get(i);
        viewHolder.bind(order, viewModel);
    }


    @Override
    public int getItemCount() {
        if (this.orders == null)
            return 0;
        return this.orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj, OrdersViewModel viewModel) {
            binding.setVariable(BR.obj, obj);
            binding.setVariable(BR.viewModel, viewModel);
            binding.executePendingBindings();
        }
    }
}
