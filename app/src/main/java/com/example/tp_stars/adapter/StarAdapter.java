package com.example.tp_stars.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_stars.beans.Star;

import java.util.ArrayList;
import java.util.List;

import tp.ensa.ma.stargallery.R;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable {

    private Context context;
    private List<Star> stars; // Liste actuelle affichée (filtrée ou non)
    private List<Star> originalStars; // Liste originale non filtrée

    public StarAdapter(Context context, List<Star> stars) {
        this.context = context;
        this.stars = stars;
        this.originalStars = new ArrayList<>(stars); // Faire une copie de la liste originale
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);
        return new StarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        Star star = stars.get(position);
        holder.name.setText(star.getName());
        holder.star.setRating(star.getStar());
        holder.img.setImageResource(star.getImg());

        // Ouvrir le dialog de notation au clic sur l'élément
        holder.itemView.setOnClickListener(v -> showRatingDialog(star,holder));



    }

    @Override
    public int getItemCount() {
        return stars.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<Star> filteredList = new ArrayList<>();
                if (charSequence == null || charSequence.length() == 0) {
                    filteredList.addAll(originalStars);
                } else {
                    String filterPattern = charSequence.toString().toLowerCase().trim();
                    for (Star star : originalStars) {
                        if (star.getName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(star);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                stars.clear();
                if (filterResults.values != null) {
                    stars.addAll((List<Star>) filterResults.values);
                }
                notifyDataSetChanged();
            }
        };
    }

    private void showRatingDialog(Star star, StarViewHolder holder) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_rating, null);
        builder.setView(dialogView);

        TextView starName = dialogView.findViewById(R.id.star_name);
        ImageView starImage = dialogView.findViewById(R.id.star_image);
        RatingBar ratingBar = dialogView.findViewById(R.id.rating_bar);
        EditText numInput = dialogView.findViewById(R.id.num_input); // Champ de saisie de la note

        // Remplir les informations de l'acteur
        starName.setText(star.getName());
        starImage.setImageResource(star.getImg());
        ratingBar.setRating(star.getStar());

        // Créer le dialogue
        builder.setTitle("Évaluer " + star.getName());
        builder.setPositiveButton("OK", (dialog, which) -> {
            // Lire la note du champ de saisie
            String input = numInput.getText().toString();
            try {
                float rating = Float.parseFloat(input);
                if (rating >= 1 && rating <= 5) {
                    star.setStar(rating); // Mettre à jour la note dans l'objet Star
                    ratingBar.setRating(rating); // Mettre à jour le RatingBar
                    holder.star.setRating(rating); // Mettre à jour le RatingBar dans le ViewHolder
                    notifyDataSetChanged(); // Notifier l'adaptateur de la mise à jour
                } else {
                    // Afficher un message d'erreur si la note n'est pas valide
                    Toast.makeText(context, "Veuillez entrer une note entre 1 et 5.", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                // Afficher un message d'erreur si la saisie n'est pas un nombre
                Toast.makeText(context, "Veuillez entrer un nombre valide.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Annuler", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }



    static class StarViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        RatingBar star;

        StarViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            star = itemView.findViewById(R.id.stars);
        }
    }
}
