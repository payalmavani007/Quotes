package quotes.pro.sau.quotes;

import java.util.List;

public class Author_SwiipeModel {


    /**
     * status : 0
     * msg : show Successfully
     * image_url : http://192.168.1.200/quotesmanagement/public/uploads/
     * data : [{"id":2,"quotes_name":"It is better to fail in originality than to succeed in imitation.","quotes_image":"1529670148-b.jpg","authormanagement_id":2},{"id":4,"quotes_name":"Hold fast to dreams, for if dreams die, life is a broken-winged bird that cannot fly.","quotes_image":"1529670186-download (4).jpg","authormanagement_id":2},{"id":7,"quotes_name":"If only we could pull out our brain and use only our eyes.","quotes_image":"1529670263-images (13).jpg","authormanagement_id":2},{"id":11,"quotes_name":"Falsity in intellectual action is intellectual immorality.","quotes_image":"1529670359-navigation_cover.jpg","authormanagement_id":2}]
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
         * id : 2
         * quotes_name : It is better to fail in originality than to succeed in imitation.
         * quotes_image : 1529670148-b.jpg
         * authormanagement_id : 2
         */

        private int id;
        private String quotes_name;
        private String quotes_image;
        private int authormanagement_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuotes_name() {
            return quotes_name;
        }

        public void setQuotes_name(String quotes_name) {
            this.quotes_name = quotes_name;
        }

        public String getQuotes_image() {
            return quotes_image;
        }

        public void setQuotes_image(String quotes_image) {
            this.quotes_image = quotes_image;
        }

        public int getAuthormanagement_id() {
            return authormanagement_id;
        }

        public void setAuthormanagement_id(int authormanagement_id) {
            this.authormanagement_id = authormanagement_id;
        }
    }
}
