package com.example.onlinestore.contents.pages.profilepage;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.onlinestore.R;
import com.example.onlinestore.data.AppSharedViewModel;
import com.example.onlinestore.data.ProductEntity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class EditCardFragment extends Fragment {

    EditCardFragmentArgs args;
    String cardToEditStr;
    ProductEntity cardToEdit;
    ArrayList<String> categoryDropdownArrayList = new ArrayList<>();
    ArrayList<String> genderDropdownArrayList = new ArrayList<>();

    ShapeableImageView itemImage;
    TextInputEditText itemTitle, itemDescription, itemPrice, itemDiscount, itemSize, itemCity;
    MaterialAutoCompleteTextView itemCategory;
    ChipGroup itemGenderChipGroup;
    MaterialButton applyChangesButton, changePictureButton;

    AppSharedViewModel sharedViewModel;



    MaterialToolbar toolbar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_card, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(getActivity()).get(AppSharedViewModel.class);

        args = EditCardFragmentArgs.fromBundle(getArguments());
        cardToEditStr = args.getCardToEdit();
        cardToEdit = new Gson().fromJson(cardToEditStr, ProductEntity.class);

        setupNavigationIconBackButton(view);
        setupDropdownLists(view);
        initializeElements(view);
        setValuesToFields();


        //TODO: SET VALUES AND STUFF
        //          ......
        //--------------------------



        sharedViewModel.updateProduct(cardToEdit);
    }

    private void setValuesToFields() {

        if (cardToEdit.getItemPicture()!=null){
            Glide.with(getContext()).load(Uri.parse(cardToEdit.getItemPicture())).into(itemImage);

        }
        itemTitle.setText(cardToEdit.getItemTitle());
        itemDescription.setText(cardToEdit.getItemDescription());
        itemPrice.setText(cardToEdit.getItemRawPrice());
        itemDiscount.setText(cardToEdit.getItemDiscount());
        itemCategory.setText(cardToEdit.getItemCategory());
        String gender = cardToEdit.getItemGender();
        switch (gender){
            case "Men":
                itemGenderChipGroup.check(R.id.chip_men_edit_card);
                break;
            case "Women":
                itemGenderChipGroup.check(R.id.chip_women_edit_card);
                break;
            case "Kid":
                itemGenderChipGroup.check(R.id.chip_kids_edit_card);
                break;
        }
        itemSize.setText(cardToEdit.getItemSize());
        itemCity.setText(cardToEdit.getItemCity());
    }
    private void initializeElements(@NonNull View view) {
        itemImage = view.findViewById(R.id.edit_card_item_image_view);
        itemTitle = view.findViewById(R.id.edit_card_title_textview);
        itemDescription = view.findViewById(R.id.edit_card_description_textview);
        itemPrice = view.findViewById(R.id.edit_card_price_textview);
        itemDiscount = view.findViewById(R.id.edit_card_discount_textview);
        itemCategory = view.findViewById(R.id.edit_card_category_autocomplete);
        itemGenderChipGroup = view.findViewById(R.id.chipGroup_edit_card);
        itemSize = view.findViewById(R.id.edit_card_size_text_view);
        itemCity = view.findViewById(R.id.edit_card_city_textview);
        changePictureButton = view.findViewById(R.id.edit_card_change_picture_button);
        applyChangesButton = view.findViewById(R.id.edit_card_next_button);
    }
    private void setupNavigationIconBackButton(@NonNull View view) {
        toolbar = view.findViewById(R.id.top_app_bar_edit_card);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }
    private void setupDropdownLists(@NonNull View view) {

        categoryDropdownArrayList.add("Accessories");
        categoryDropdownArrayList.add("Clothing");
        categoryDropdownArrayList.add("Shoes");
        categoryDropdownArrayList.add("Underwear");

        genderDropdownArrayList.add("Men");
        genderDropdownArrayList.add("Women");
        genderDropdownArrayList.add("Kids");

        ArrayAdapter<String> categoryDropdownListAdapter = new ArrayAdapter<String>(requireContext()
                , R.layout.list_item_layout, categoryDropdownArrayList);
        MaterialAutoCompleteTextView categoryDropDown = view.findViewById(R.id.edit_card_category_autocomplete);
        categoryDropDown.setAdapter(categoryDropdownListAdapter);
    }

}