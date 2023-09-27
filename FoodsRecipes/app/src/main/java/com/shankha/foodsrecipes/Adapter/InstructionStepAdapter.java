package com.shankha.foodsrecipes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shankha.foodsrecipes.Model.Step;
import com.shankha.foodsrecipes.R;

import java.util.List;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionStepViewHolder>{
    Context context;
    List<Step> list;

    public InstructionStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_step,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {
        holder.txtV_instruction_step_number.setText(String.valueOf(list.get(position).number));
        holder.txtV_instruction_step_title.setText(list.get(position).step);
        holder.recycle_instruction_ingredents.setHasFixedSize(true);
        holder.recycle_instruction_ingredents.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        InstructionIngredentAdapter instructionIngredentAdapter=new InstructionIngredentAdapter(context,list.get(position).ingredients);
        holder.recycle_instruction_ingredents.setAdapter(instructionIngredentAdapter);
        holder.recycle_instruction_equipment.setHasFixedSize(true);
        holder.recycle_instruction_equipment.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        InstructionEquipmentAdapter instructionEquipmentAdapter =new InstructionEquipmentAdapter(context,list.get(position).equipment);
        holder.recycle_instruction_equipment.setAdapter(instructionEquipmentAdapter);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
 class InstructionStepViewHolder extends RecyclerView.ViewHolder{
        TextView txtV_instruction_step_number,txtV_instruction_step_title;
        RecyclerView recycle_instruction_ingredents,recycle_instruction_equipment;
     public InstructionStepViewHolder(@NonNull View itemView) {
         super(itemView);
         recycle_instruction_ingredents=itemView.findViewById(R.id.recycle_instruction_ingredents);
         recycle_instruction_equipment=itemView.findViewById(R.id.recycle_instruction_equipment);
         txtV_instruction_step_title=itemView.findViewById(R.id.txtV_instruction_step_title);
         txtV_instruction_step_number=itemView.findViewById(R.id.txtV_instruction_step_number);

     }
 }
