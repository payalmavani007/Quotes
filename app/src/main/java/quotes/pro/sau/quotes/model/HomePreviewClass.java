package quotes.pro.sau.quotes.model;

import java.util.List;

public class HomePreviewClass  {


    /**
     * status : 0
     * msg : show Successfully
     * image_url : http://192.168.1.200/quotesmanagement/public/uploads/
     * data : [{"id":18,"quotes_image":"1529670584-images (15).jpg","quotes_name":"You may hold my hand for a while, but you hold my heart forever."},{"id":1,"quotes_image":"1529670127-d.jpg","quotes_name":"Success is not final; failure is not fatal: It is the courage to continue that counts."},{"id":2,"quotes_image":"1529670148-b.jpg","quotes_name":"It is better to fail in originality than to succeed in imitation."},{"id":3,"quotes_image":"1529670169-d.jpg","quotes_name":"A dream doesn't become reality through magic; it takes sweat, determination and hard work."},{"id":4,"quotes_image":"1529670186-download (4).jpg","quotes_name":"Hold fast to dreams, for if dreams die, life is a broken-winged bird that cannot fly."},{"id":5,"quotes_image":"1529670213-images (14).jpg","quotes_name":"The most beautiful gift of nature is that it gives one pleasure to look around and try to comprehend what we see."},{"id":6,"quotes_image":"1529670233-images (2).jpg","quotes_name":"Setting goals is the first step in turning the invisible into the visible."},{"id":7,"quotes_image":"1529670263-images (13).jpg","quotes_name":"If only we could pull out our brain and use only our eyes."},{"id":8,"quotes_image":"1529670285-images (8).jpg","quotes_name":"There is no way I am not going to be the person I am not meant to, beautiful eyes, I tell you."},{"id":9,"quotes_image":"1529670312-download (1).jpg","quotes_name":"Guys love girls who have lovely smiles and lovely eyes, that is the truth behind all of it."},{"id":10,"quotes_image":"1529670338-images (18).jpg","quotes_name":"Yesterday is history, tomorrow is a mystery, today is God\u2019s gift, that\u2019s why we call it the present."},{"id":11,"quotes_image":"1529670359-navigation_cover.jpg","quotes_name":"Falsity in intellectual action is intellectual immorality."},{"id":12,"quotes_image":"1529670385-images (12).jpg","quotes_name":"The more that you read, the more things you will know, the more that you learn, the more places you\u2019ll go."},{"id":13,"quotes_image":"1529670408-images (19).jpg","quotes_name":"Curiosity about life in all of its aspects, I think, is still the secret of great creative people."},{"id":14,"quotes_image":"1529670479-images (13).jpg","quotes_name":"The chief enemy of creativity is good sense. Pablo Picasso Click to tweet."},{"id":15,"quotes_image":"1529670498-images.jpg","quotes_name":"You can\u2019t wait for inspiration, you have to go after it with a club."},{"id":16,"quotes_image":"1529670526-images (19).jpg","quotes_name":"Curiosity about life in all of its aspects, I think, is still the secret of great creative people."},{"id":17,"quotes_image":"1529670562-download (1).jpg","quotes_name":"Falsity in intellectual action is intellectual immorality."},{"id":19,"quotes_image":"1529671445-images (9).jpg","quotes_name":"There is no law except the law that there is no law."},{"id":20,"quotes_image":"1529671466-images (8).jpg","quotes_name":"Be practical as well as generous in your ideals. Keep your eyes on the stars, but remember to keep your feet on the ground."},{"id":21,"quotes_image":"1529671489-images (4).jpg","quotes_name":"Children just need the time, the space, and the permission to be kids."},{"id":22,"quotes_image":"1529671512-images (7).jpg","quotes_name":"Falsity in intellectual action is intellectual immorality."},{"id":23,"quotes_image":"1529671534-images (15).jpg","quotes_name":"If only we could pull out our brain and use only our eyes."},{"id":24,"quotes_image":"1529671902-navigation_cover.jpg","quotes_name":"Success is not final; failure is not fatal: It is the courage to continue that counts."},{"id":25,"quotes_image":"1529671924-images (17).jpg","quotes_name":"It does not do to dwell on dreams and forget to live."},{"id":26,"quotes_image":"1529671957-images (15).jpg","quotes_name":"Hold fast to dreams, for if dreams die, life is a broken-winged bird that cannot fly."},{"id":27,"quotes_image":"1529671987-images (12).jpg","quotes_name":"All our dreams can come true, if we have the courage to pursue them."},{"id":28,"quotes_image":"1529672022-images (19).jpg","quotes_name":"Don't be afraid to give up the good to go for the great."},{"id":29,"quotes_image":"1529672057-images (8).jpg","quotes_name":"Success usually comes to those who are too busy to be looking for it."},{"id":30,"quotes_image":"1529672092-images (11).jpg","quotes_name":"Opportunities don't happen. You create them."},{"id":31,"quotes_image":"1529672129-images (12).jpg","quotes_name":"Hold fast to dreams, for if dreams die, life is a broken-winged bird that cannot fly."},{"id":32,"quotes_image":"1530074198-61124.jpg","quotes_name":"The world's most sensible person and the biggest idiot both stay within us. The worst part is, you can't even tell who is who."},{"id":33,"quotes_image":null,"quotes_name":"Your positive action combined with positive thinking results in success"},{"id":34,"quotes_image":null,"quotes_name":"positive action combined with positive thinking results in success"}]
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
         * id : 18
         * quotes_image : 1529670584-images (15).jpg
         * quotes_name : You may hold my hand for a while, but you hold my heart forever.
         */

        private int id;
        private String quotes_image;
        private String quotes_name;

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
    }
}
