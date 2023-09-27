package com.shankha.foodsrecipes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shankha.foodsrecipes.Model.InstructionResponse;
import com.shankha.foodsrecipes.R;

import java.util.List;

public class InstructionAdapter extends RecyclerView.Adapter<InstructionViewHolder> {
    Context context;
    List<InstructionResponse> list;

    public InstructionAdapter(Context context, List<InstructionResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionViewHolder holder, int position) {
        holder.textV_meal_instruction.setText(list.get(position).name);
        holder.recycle_meal_steps.setHasFixedSize(true);
        holder.recycle_meal_steps.setHasFixedSize(true);
        holder.recycle_meal_steps.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        InstructionStepAdapter instructionStepAdapter=new InstructionStepAdapter(context,list.get(position).steps);
        holder.recycle_meal_steps.setAdapter(instructionStepAdapter);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionViewHolder extends RecyclerView.ViewHolder{
    TextView textV_meal_instruction;
    RecyclerView recycle_meal_steps;
    public InstructionViewHolder(@NonNull View itemView) {
        super(itemView);
        recycle_meal_steps=itemView.findViewById(R.id.recycle_meal_steps);
        textV_meal_instruction=itemView.findViewById(R.id.textV_meal_instruction);
    }
}
