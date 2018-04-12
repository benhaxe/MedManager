package com.android.benhaxe.medmanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.android.benhaxe.medmanager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin Masebinu on 11-Apr-18.
 * Lead Organizer GDG FUTA
 * Android & CMS Developer at Planet Nest
 * haxeboom@gmail.com
 */

public class MonthlyAdapter extends RecyclerView.Adapter<MedicineHolder>
        implements Filterable{
    private Context context;
    private List<MedicinePojo> medicinePojoList;

    private List<MedicinePojo> medicineListFiltered;

    public MonthlyAdapter(List<MedicinePojo> medicinePojoList, Context context) {
        this.context = context;
        this.medicinePojoList = medicinePojoList;
        this.medicineListFiltered = medicinePojoList;
    }

    @NonNull
    @Override
    public MedicineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_drug_view, parent, false);
        return new MedicineHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineHolder holder, int position) {
        final MedicinePojo model = this.medicinePojoList.get(position);

        holder.setName(model.getDrugs());
        holder.setDosage(model.getDosage());
        holder.setEndDate(model.getStop_date());
    }

    @Override
    public int getItemCount() {
        return medicinePojoList.size();
    }

    //Add Data to the Adapter
    public void add(MedicinePojo medicinePojo) {
        medicinePojoList.add(medicinePojo);
        notifyDataSetChanged();
    }


    //Clear Data to the Adapter for stuff like swipe to refresh
    public void clear(){
        medicinePojoList.clear();
        notifyDataSetChanged();
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()){
                    medicineListFiltered = medicinePojoList;
                }else{
                    List<MedicinePojo> filteredList = new ArrayList<>();
                    for (MedicinePojo row : medicinePojoList){
                        if (row.getDrugs().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(row);
                        }
                    }
                    medicineListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = medicineListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                medicineListFiltered = (ArrayList<MedicinePojo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
