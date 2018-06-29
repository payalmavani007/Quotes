package quotes.pro.sau.quotes.model;

import java.util.List;

public class Category_SwiipeModel {


    /**
     * status : 0
     * msg : show Successfully
     * image_url : http://192.168.1.200/quotesmanagement/public/uploads/
     * data : [{"id":17,"quotes_image":"1529670562-download (1).jpg","quotes_name":"Falsity in intellectual action is intellectual immorality.","authormanagement_id":9,"categorymanagement_id":4,"created_at":"2018-06-22 12:29:22","updated_at":"2018-06-22 12:29:22"},{"id":6,"quotes_image":"1529670233-images (2).jpg","quotes_name":"Setting goals is the first step in turning the invisible into the visible.","authormanagement_id":8,"categorymanagement_id":4,"created_at":"2018-06-22 12:23:53","updated_at":"2018-06-22 12:23:53"},{"id":17,"quotes_image":"1529670562-download (1).jpg","quotes_name":"Falsity in intellectual action is intellectual immorality.","authormanagement_id":9,"categorymanagement_id":4,"created_at":"2018-06-22 12:29:22","updated_at":"2018-06-22 12:29:22"},{"id":20,"quotes_image":"1529671466-images (8).jpg","quotes_name":"Be practical as well as generous in your ideals. Keep your eyes on the stars, but remember to keep your feet on the ground.","authormanagement_id":13,"categorymanagement_id":4,"created_at":"2018-06-22 12:44:26","updated_at":"2018-06-22 12:44:26"}]
     */

    private int status;
    private String msg;
    private String image_url;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 17
         * quotes_image : 1529670562-download (1).jpg
         * quotes_name : Falsity in intellectual action is intellectual immorality.
         * authormanagement_id : 9
         * categorymanagement_id : 4
         * created_at : 2018-06-22 12:29:22
         * updated_at : 2018-06-22 12:29:22
         */

        private int id;
        private String quotes_image;
        private String quotes_name;
        private int authormanagement_id;
        private int categorymanagement_id;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuotes_image() {
            return quotes_image;
        }

        public void setQuotes_image(String quotes_image) {
            this.quotes_image = quotes_image;
        }

        public String getQuotes_name() {
            return quotes_name;
        }

        public void setQuotes_name(String quotes_name) {
            this.quotes_name = quotes_name;
        }

        public int getAuthormanagement_id() {
            return authormanagement_id;
        }

        public void setAuthormanagement_id(int authormanagement_id) {
            this.authormanagement_id = authormanagement_id;
        }

        public int getCategorymanagement_id() {
            return categorymanagement_id;
        }

        public void setCategorymanagement_id(int categorymanagement_id) {
            this.categorymanagement_id = categorymanagement_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
