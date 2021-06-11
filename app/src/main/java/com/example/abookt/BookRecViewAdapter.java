package com.example.abookt;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static com.example.abookt.BookActivity.BOOK_ID_KEY;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {
    private static final String TAG = "BookRecViewAdapter";

    private List<BookModel> books = new ArrayList<>();
    private Context mContext;
    private String parentActivity;

    public BookRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        ViewHolder holder = new ViewHolder(view);
        // return new ViewHolder(view);
        return holder;
    }

    // Logic here, sir!
    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull BookRecViewAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.textBookName.setText(books.get(position).getName());
        Glide.with(mContext)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imgBook);

        holder.cardViewParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BookActivity.class);
                intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
                mContext.startActivity(intent);
            }
        });

        holder.textAuthor.setText(books.get(position).getAuthor());
        holder.textShortDescription.setText(books.get(position).getShortDesc());

        //when expande
        if(books.get(position).isExpanded()){
            TransitionManager.beginDelayedTransition(holder.cardViewParent);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.buttonDownArrow.setVisibility(View.GONE);

            if(parentActivity.equals("allBooks")){
                holder.buttonDelete.setVisibility(View.GONE);
                //TODO: terrible hack here. use method
            } else if(parentActivity.equals("AlreadyReadBooks")){
                holder.buttonDelete.setVisibility(View.VISIBLE);
                holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Do you want to delete " + books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance().removeFromAlreadyReadBooks(books.get(position))){
                                    Toast.makeText(mContext, "book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        });
                        builder.create().show();
                    }
                });
            }else if(parentActivity.equals("ReadList")){
                holder.buttonDelete.setVisibility(View.VISIBLE);
                holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Do you want to delete " + books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance().removeFromReadList(books.get(position))){
                                    Toast.makeText(mContext, "book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        });
                        builder.create().show();
                    }
                });
            }else if(parentActivity.equals("CurrentlyReading")){
                holder.buttonDelete.setVisibility(View.VISIBLE);
                holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Do you want to delete " + books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance().removeFromCurrentlyReading(books.get(position))){
                                    Toast.makeText(mContext, "book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        });
                        builder.create().show();
                    }
                });
            }else if(parentActivity.equals("FavoriteBooks")){
                holder.buttonDelete.setVisibility(View.VISIBLE);
                holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Do you want to delete " + books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance().removeFromFavoriteBooks(books.get(position))){
                                    Toast.makeText(mContext, "book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        });
                        builder.create().show();
                    }
                });
            }

        } else{
            TransitionManager.beginDelayedTransition(holder.cardViewParent);
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.buttonDownArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<BookModel> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    // Logic here, sir!
    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardViewParent;
        private ImageView imgBook, buttonDownArrow, buttonUpArrow;
        private TextView textBookName, textAuthor, textShortDescription, buttonDelete;
        private RelativeLayout collapsedRelLayout, expandedRelLayout;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            cardViewParent = itemView.findViewById(R.id.cardViewParent);
            imgBook = itemView.findViewById(R.id.imgBook);
            textBookName = itemView.findViewById(R.id.textBookName);

            buttonDownArrow = itemView.findViewById(R.id.buttonDownArrow);
            buttonUpArrow = itemView.findViewById(R.id.buttonUpArrow);

            textAuthor = itemView.findViewById(R.id.textAuthor);
            textShortDescription = itemView.findViewById(R.id.textShortDescription);

            collapsedRelLayout = itemView.findViewById(R.id.collapsedRelLayout);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);

            buttonDelete = itemView.findViewById(R.id.buttonDelete);

            buttonDownArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BookModel book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            buttonUpArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BookModel book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}










