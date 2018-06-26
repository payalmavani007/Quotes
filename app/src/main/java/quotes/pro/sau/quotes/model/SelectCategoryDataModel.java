package quotes.pro.sau.quotes.model;

import java.util.List;

public class SelectCategoryDataModel {

    /**
     * status : 0
     * msg : show Successfully
     * image_url : http://192.168.1.200/quotesmanagement/public/uploads/
     * data : [{"id":21,"quotes_image":"1527769320-images (14).jpg","quotes_name":"I will not let anyone walk through my mind with their dirty feet.","authormanagement_id":1,"categorymanagement_id":1,"created_at":"2018-05-31 12:22:00","updated_at":"2018-05-31 12:22:00"},{"id":20,"quotes_image":"1527769282-images (13).jpg","quotes_name":"Freedom is not worth having if it does not include the freedom to make mistakes.","authormanagement_id":1,"categorymanagement_id":1,"created_at":"2018-05-31 12:21:22","updated_at":"2018-05-31 12:21:22"},{"id":19,"quotes_image":"1527769236-images (12).jpg","quotes_name":"Earth provides enough to satisfy every man's needs, but not every man's greed.","authormanagement_id":1,"categorymanagement_id":1,"created_at":"2018-05-31 12:20:36","updated_at":"2018-05-31 12:20:36"},{"id":18,"quotes_image":"1527769200-images (10).jpg","quotes_name":"A man is but the product of his thoughts; what he thinks, he becomes.","authormanagement_id":1,"categorymanagement_id":1,"created_at":"2018-05-31 12:20:00","updated_at":"2018-05-31 12:20:00"},{"id":17,"quotes_image":"1527769165-images (9).jpg","quotes_name":"Strength does not come from physical capacity. It comes from an indomitable will.","authormanagement_id":1,"categorymanagement_id":1,"created_at":"2018-05-31 12:19:25","updated_at":"2018-05-31 12:19:25"},{"id":16,"quotes_image":"1527769116-images (8).jpg","quotes_name":"The weak can never forgive. Forgiveness is the attribute of the strong.","authormanagement_id":1,"categorymanagement_id":1,"created_at":"2018-05-31 12:18:36","updated_at":"2018-05-31 12:18:36"},{"id":14,"quotes_image":"1527769049-images (6).jpg","quotes_name":"Happiness is when what you think, what you say, and what you do are in harmony.","authormanagement_id":1,"categorymanagement_id":1,"created_at":"2018-05-31 12:17:29","updated_at":"2018-05-31 12:17:29"},{"id":13,"quotes_image":"1527768991-images (5).jpg","quotes_name":"Where there is love there is life.","authormanagement_id":1,"categorymanagement_id":1,"created_at":"2018-05-31 12:16:31","updated_at":"2018-05-31 12:16:31"},{"id":12,"quotes_image":"1527768961-images (4).jpg","quotes_name":"Where can we go to find God if we cannot see Him in our own hearts and in every living being.","authormanagement_id":3,"categorymanagement_id":1,"created_at":"2018-05-31 12:16:01","updated_at":"2018-05-31 12:16:01"},{"id":11,"quotes_image":"1527768924-images (3).jpg","quotes_name":"All differences in this world are of degree, and not of kind, because oneness is the secret of everything.","authormanagement_id":3,"categorymanagement_id":1,"created_at":"2018-05-31 12:15:24","updated_at":"2018-05-31 12:15:24"},{"id":9,"quotes_image":"1527768838-images (1).jpg","quotes_name":"The world is the great gymnasium where we come to make ourselves strong.","authormanagement_id":3,"categorymanagement_id":1,"created_at":"2018-05-31 12:13:58","updated_at":"2018-05-31 12:13:58"},{"id":8,"quotes_image":"1527768795-download.jpg","quotes_name":"You cannot believe in God until you believe in yourself.","authormanagement_id":3,"categorymanagement_id":1,"created_at":"2018-05-31 12:13:15","updated_at":"2018-05-31 12:13:15"},{"id":6,"quotes_image":"1527768734-download (6).jpg","quotes_name":"Do we not realize that self respect comes with self reliance?","authormanagement_id":2,"categorymanagement_id":1,"created_at":"2018-05-31 12:12:14","updated_at":"2018-05-31 12:12:14"},{"id":5,"quotes_image":"1527768692-download (5).jpg","quotes_name":"Be more dedicated to making solid achievements than in running after swift but synthetic happiness.","authormanagement_id":2,"categorymanagement_id":1,"created_at":"2018-05-31 12:11:32","updated_at":"2018-05-31 12:11:32"},{"id":4,"quotes_image":"1527768666-download (4).jpg","quotes_name":"Great dreams of great dreamers are always transcended.","authormanagement_id":2,"categorymanagement_id":1,"created_at":"2018-05-31 12:11:06","updated_at":"2018-05-31 12:11:06"}]
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
         * id : 21
         * quotes_image : 1527769320-images (14).jpg
         * quotes_name : I will not let anyone walk through my mind with their dirty feet.
         * authormanagement_id : 1
         * categorymanagement_id : 1
         * created_at : 2018-05-31 12:22:00
         * updated_at : 2018-05-31 12:22:00
         */

        private int id;
        private String quotes_image;
        private String quotes_name;

        public String getAuthormanagement_id() {
            return authormanagement_id;
        }

        public void setAuthormanagement_id(String authormanagement_id) {
            this.authormanagement_id = authormanagement_id;
        }

        private String authormanagement_id;
        private int categorymanagement_id;
        private String created_at;
        private String updated_at;

        public int getId(int i) {
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
