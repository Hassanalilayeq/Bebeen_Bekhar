package com.example.bebeenbekhar.category

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.example.bebeenbekhar.R
import com.example.bebeenbekhar.databinding.FragmentAddItemBinding
import com.example.bebeenbekhar.home.FragmentHome
import com.example.bebeenbekhar.net.ApiService
import com.example.bebeenbekhar.utils.BASE_URL
import com.example.bebeenbekhar.utils.SENT_DATA_KEY_TITLE
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Add_Item_Fragment : Fragment() {

    lateinit var binding: FragmentAddItemBinding
    lateinit var apiService: ApiService
    private val REQUEST_IMAGE_PICK = 1
    private val imageViews = arrayOfNulls<ImageView>(4)
    private var selectedImages = arrayOfNulls<Uri>(4)
    private var imageCounter = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddItemBinding.inflate(layoutInflater)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ImageView array
        imageViews[0] = binding.img1
        imageViews[1] = binding.img2
        imageViews[2] = binding.img3
        imageViews[3] = binding.img4

        // Set click listeners for each image view
        for (i in 0 until imageViews.size) {
            imageViews[i]?.setOnClickListener {
                if (selectedImages[i] != null) {
                    showDeleteConfirmationDialog(i)
                }
            }
        }

        // button for cleaning the form

        binding.txtCleanForm.setOnClickListener {
            binding.txtTypeTools.text = ""
            binding.edtTitleChoose.text = null
            binding.edtPriceTools.text = null
            binding.edtStateTools.text = null
            binding.edtPhoneNumber.text = null
            binding.edtDescripeTools.text = null
            binding.edtAddress.text = null
            binding.edtDate.text = null
        }



        // Button to open the gallery
        val openGalleryButton: Button = binding.btnChoosePic
        openGalleryButton.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, REQUEST_IMAGE_PICK)
        }

        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)



        binding.btnAddItem.setOnClickListener {
            addNewSellItem()

        }

        // Takes the type of items_Title

        val title = arguments?.getString(SENT_DATA_KEY_TITLE)
        binding.txtTypeTools.text = title

        binding.btnTypeTools.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.frameContainerView, Fragment_Division_Add_Items())
            transaction.commit()
        }

    }

    private fun addNewSellItem() {
        val imgurl = binding.img1.id
        val item_price = binding.edtPriceTools.text.toString()
        val item_title = binding.edtTitleChoose.text.toString()
        val item_type = binding.txtTypeTools.text.toString()
        val item_State = binding.edtStateTools.text.toString()
        val itemPriceArz = binding.spinnerArz.selectedItem.toString()
        val phoneNumber = binding.edtPhoneNumber.text.toString()
        val item_Description = binding.edtDescripeTools.text.toString()
        val item_Address = binding.edtAddress.text.toString()
        val item_Address1 = binding.spinnerProvince.selectedItem.toString()
        val item_district = binding.spinnerState.selectedItem.toString().toInt()
        val item_Added_Date = binding.edtDate.text.toString()

        if (imgurl != null && item_price.isNotEmpty() &&
            item_title.isNotEmpty() && item_type.isNotEmpty() &&
            item_State.isNotEmpty() && itemPriceArz.isNotEmpty() && phoneNumber.isNotEmpty() &&
            item_Description.isNotEmpty() && item_Address.isNotEmpty() &&
            item_Address.length < 20 && item_price.length < 10 &&
            item_Address1.isNotEmpty() && item_district != null &&
            item_Added_Date.isNotEmpty() && item_title.length > 3 && item_title.length < 20 &&
            phoneNumber.length ==10 )  {

            val jsonObject = JsonObject()
            jsonObject.addProperty("imageUrl", imgurl)
            jsonObject.addProperty("itemType", item_type)
            jsonObject.addProperty("itemTitle", item_title)
            jsonObject.addProperty("itemState", item_State)
            jsonObject.addProperty("itemDescription", item_Description)
            jsonObject.addProperty("itemPrice", item_price)
            jsonObject.addProperty("itemPriceArz", itemPriceArz)
            jsonObject.addProperty("phoneNumber", phoneNumber)
            jsonObject.addProperty("itemAddress", item_Address1)
            jsonObject.addProperty("itemProvince", item_Address)
            jsonObject.addProperty("itemDistrict", item_district)
            jsonObject.addProperty("sell_Item_Added_Date", item_Added_Date)

            apiService.insertSellItem(jsonObject).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Toast.makeText(context, "یک ایتم اظافه شد", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.v("apiLog", "Error")
                }
            })
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.frameContainerView, FragmentHome())
            transaction.commit()
        }else{
            Toast.makeText(context, "لطفا تمام اطلاعات را وارید کنید.", Toast.LENGTH_SHORT).show()
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            if (selectedImageUri != null && imageCounter < 4) {
                selectedImages[imageCounter] = selectedImageUri
                imageViews[imageCounter]?.setImageURI(selectedImageUri)
                imageViews[imageCounter]?.setPadding(2)
                imageCounter++

            }
        }
    }

    private fun showDeleteConfirmationDialog(imageIndex: Int) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder
            .setMessage("میخواهید عکس حذف شود؟")
            .setPositiveButton("بلی") { dialogInterface: DialogInterface, _: Int ->
                deleteImage(imageIndex)
                dialogInterface.dismiss()
            }
            .setNegativeButton("نخیر") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }

        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        // Set positive button color to green
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(Color.GREEN)

        // Set negative button color to red
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(Color.RED)
    }

    private fun deleteImage(imageIndex: Int) {
        if (imageIndex < imageCounter) {
            // Shift the remaining images to fill the gap caused by deletion
            for (i in imageIndex until imageCounter - 1) {
                selectedImages[i] = selectedImages[i + 1]
                imageViews[i]?.setImageURI(selectedImages[i])
            }
            // Clear the last image view and URI
            imageViews[imageCounter - 1]?.setImageResource(R.drawable.ic_photos)
            imageViews[imageCounter - 1]?.setPadding(110)
            selectedImages[imageCounter - 1] = null
            imageCounter--
        }
    }

}