package quotes.pro.sau.quotes.retrofit;

import quotes.pro.sau.quotes.model.Homelist_model;
import quotes.pro.sau.quotes.model.SelectCategoryDataModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface ApiInterface {
    @FormUrlEncoded
    @POST("selected_CategoryData")
    Call<SelectCategoryDataModel> getAllCategoryItem(@Field("category_id") String category_id);

    @POST("list_quotes")
    Call<Homelist_model> getDufultQutes();

}