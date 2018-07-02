package quotes.pro.sau.quotes.retrofit;

import quotes.pro.sau.quotes.Author_SwiipeModel;
import quotes.pro.sau.quotes.model.Category_SwiipeModel;
import quotes.pro.sau.quotes.model.HomePreviewClass;
import quotes.pro.sau.quotes.model.SelectCategoryDataModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface ApiInterface {
    @FormUrlEncoded
    @POST("selected_CategoryData")
    Call<SelectCategoryDataModel> getAllCategoryItem(@Field("category_id") String category_id);

    @FormUrlEncoded
    @POST("swipe_card")
    Call<Category_SwiipeModel> getCategorySwipeList(@Field("category_id") String category_id, @Field("SelectedCatagoryId") String SelectedCatagoryId);

    @FormUrlEncoded
    @POST("home_swipe_card")
    Call<HomePreviewClass> getDufultQutes(@Field("SelectedId") String SelectedId);

    @FormUrlEncoded
    @POST("author_swipe_card")
    Call<Author_SwiipeModel> getAuthorSwipeList(@Field("author_id") String author_id, @Field("SelectedAuthorId") String SelectedAuthorId);


}