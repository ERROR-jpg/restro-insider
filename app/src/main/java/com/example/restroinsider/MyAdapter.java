package com.example.restroinsider;

import static com.example.restroinsider.notifications.Utils.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nl.dionsegijn.steppertouch.StepperTouch;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewHolder> {
    List<Model> models;
    MyAdapter(List<Model> models){
        this.models = models;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int i) {
        holder.textView.setText(models.get(i).menuitemname);
        holder.st.setMinValue(0);
        holder.st.addStepCallback((i1, b) -> {
            if(i1> 0){
                order.put(holder.textView.getText().toString(),i1);
            }else if(i1 == 0 && order.containsKey(holder.textView.getText().toString())){
                order.remove(holder.textView.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        StepperTouch st;
        public viewHolder(@NonNull View iv) {
            super(iv);
            textView= iv.findViewById(R.id.recyTxt);
            st = iv.findViewById(R.id.itemCount);
        }
    }
}
