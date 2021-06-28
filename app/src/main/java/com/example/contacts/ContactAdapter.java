package com.example.contacts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.viewHolder> {
    int index;
    private final Context context;
    private final ArrayList<String> ids, names, numbers;

    ContactAdapter(Context context, ArrayList<String> ids, ArrayList<String> names, ArrayList<String> numbers){
        this.context = context;
        this.ids = ids;
        this.names = names;
        this.numbers = numbers;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.contact, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int index) {
        holder.id.setText(String.valueOf(ids.get(index)));
        holder.name.setText(String.valueOf(names.get(index)));
        holder.number.setText(String.valueOf(numbers.get(index)));
        holder.contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);

                intent.putExtra("id", String.valueOf(ids.get(index)));
                intent.putExtra("name", String.valueOf(names.get(index)));
                intent.putExtra("number", String.valueOf(numbers.get(index)));

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        LinearLayout contact;
        TextView id, name, number;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.contact_id);
            name = itemView.findViewById(R.id.contact_name);
            number = itemView.findViewById(R.id.contact_number);
            contact = itemView.findViewById(R.id.contact);
        }
    }
}
