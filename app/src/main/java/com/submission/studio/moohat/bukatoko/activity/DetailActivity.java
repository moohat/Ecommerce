package com.submission.studio.moohat.bukatoko.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.DefaultSliderView;
import com.glide.slider.library.SliderTypes.TextSliderView;
import com.glide.slider.library.Tricks.ViewPagerEx;
import com.submission.studio.moohat.bukatoko.R;
import com.submission.studio.moohat.bukatoko.data.model.Detail;
import com.submission.studio.moohat.bukatoko.data.retrofit.Api;
import com.submission.studio.moohat.bukatoko.data.retrofit.ApiInterface;
import com.submission.studio.moohat.bukatoko.utils.Converter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {

    TextView txtName, txtPrice, txtDescription;
    private SliderLayout sliderLayout;

    ImageButton btnAddCart;
    Button btnCheckout;


    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        bundle = getIntent().getExtras();
        Log.e("_logImageIntent", bundle.getString("PRODUCT_IMAGE"));

        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtDescription = findViewById(R.id.txtDescription);
        btnAddCart = findViewById(R.id.btnAddCart);
        btnCheckout = findViewById(R.id.btnCheckout);

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });


        getDetails();


        //menampilkan panah back ke menu utama dan menampilkan nama detail barang action bar
        getSupportActionBar().setTitle("Detail Barang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void getDetails(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<Detail> call = apiInterface.getDetail(bundle.getInt("PRODUCT_ID"));
        call.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                Detail.Data data = response.body().getData();
                txtName.setText(data.getProduct());
                txtPrice.setText("IDR "+Converter.rupiah(data.getPrice()));

                if(data.getDescription() != null){
                    txtDescription.setText(data.getDescription());

                }

                Detail detail = response.body();
                List<Detail.Data.Images> images = detail.getData().getImages();

                ArrayList<String> arrayList = new ArrayList<>();
                for(Detail.Data.Images img : images){
                    arrayList.add(img.getImage());
                    Log.e("_logImages",img.getImage() );
                }

                setSlider(arrayList);
            }

            @Override
            public void onFailure(Call<Detail> call, Throwable t) {

            }
        });


    }


    private void setSlider(ArrayList<String> urlImgs){
        sliderLayout = findViewById(R.id.slider);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        //.diskCacheStrategy(DiskCacheStrategy.NONE)
        requestOptions.placeholder(R.drawable.image_icon_png_thumbnail)
        .error(R.drawable.image_icon_png_notfound);

        for (int i = 0; i < urlImgs.size(); i++) {
            DefaultSliderView sliderView = new DefaultSliderView(this);
            // if you want show image only / without description text use DefaultSliderView instead

            // initialize SliderLayout
            sliderView
                    .image(urlImgs.get(i))
                    .setRequestOption(requestOptions)
                    .setProgressBarVisible(false)
                    .setOnSliderClickListener(this);

            //add your extra information
            sliderView.bundle(new Bundle());
            sliderLayout.addSlider(sliderView);
        }

        // set Slider Transition Animation
        // mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);

        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(4000);
        sliderLayout.addOnPageChangeListener(this);

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
