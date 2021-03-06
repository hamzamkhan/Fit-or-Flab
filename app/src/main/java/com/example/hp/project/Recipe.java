package com.example.hp.project;

import android.graphics.Bitmap;
        import android.os.Parcel;
        import android.os.Parcelable;
        import android.util.Log;

public class Recipe implements Parcelable {
    private String title;
    private String recipeID;
    private String socialRank;
    private String publisher;
    private String[] ingredients;
    private String imgURL;

    private final String TEST = "Recipe";

    public Recipe() {
    }

    public Recipe(String title, String recipeID, String socialRank, String publisher, String imgURL)
    {

        this.title = title;
        this.recipeID = recipeID;
        this.socialRank = socialRank;
        this.publisher = publisher;
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    public String getSocialRank() {
        return socialRank;
    }

    public void setSocialRank(String socialRank) {
        this.socialRank = socialRank;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }



    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() //what's this?
    {

        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public Recipe(Parcel in)
    {
        this.title = in.readString();
        this.recipeID = in.readString();
        this.socialRank = in.readString();
        this.publisher = in.readString();
        this.ingredients = in.createStringArray();
        this.imgURL = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags)
    {
        parcel.writeString(title);
        parcel.writeString(recipeID);
        parcel.writeString(socialRank);
        parcel.writeString(publisher);
        parcel.writeStringArray(ingredients);
        parcel.writeString(imgURL);
    }
}

