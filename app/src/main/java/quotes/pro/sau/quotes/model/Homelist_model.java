package quotes.pro.sau.quotes.model;

import java.util.List;

public class Homelist_model {

    /**
     * status : 0
     * msg : show Successfully
     * image_url : http://192.168.1.200/quotesmanagement/public/uploads/
     * data : [{"quotes_image":null,"quotes":"positive action combined with positive thinking results in success"},{"quotes_image":null,"quotes":"Your positive action combined with positive thinking results in success"},{"quotes_image":"1530074198-61124.jpg","quotes":"The world's most sensible person and the biggest idiot both stay within us. The worst part is, you can't even tell who is who."},{"quotes_image":"1529672129-images (12).jpg","quotes":"Hold fast to dreams, for if dreams die, life is a broken-winged bird that cannot fly."},{"quotes_image":"1529672092-images (11).jpg","quotes":"Opportunities don't happen. You create them."},{"quotes_image":"1529672057-images (8).jpg","quotes":"Success usually comes to those who are too busy to be looking for it."},{"quotes_image":"1529672022-images (19).jpg","quotes":"Don't be afraid to give up the good to go for the great."},{"quotes_image":"1529671987-images (12).jpg","quotes":"All our dreams can come true, if we have the courage to pursue them."},{"quotes_image":"1529671957-images (15).jpg","quotes":"Hold fast to dreams, for if dreams die, life is a broken-winged bird that cannot fly."},{"quotes_image":"1529671924-images (17).jpg","quotes":"It does not do to dwell on dreams and forget to live."},{"quotes_image":"1529671902-navigation_cover.jpg","quotes":"Success is not final; failure is not fatal: It is the courage to continue that counts."},{"quotes_image":"1529671534-images (15).jpg","quotes":"If only we could pull out our brain and use only our eyes."},{"quotes_image":"1529671512-images (7).jpg","quotes":"Falsity in intellectual action is intellectual immorality."},{"quotes_image":"1529671489-images (4).jpg","quotes":"Children just need the time, the space, and the permission to be kids."},{"quotes_image":"1529671466-images (8).jpg","quotes":"Be practical as well as generous in your ideals. Keep your eyes on the stars, but remember to keep your feet on the ground."},{"quotes_image":"1529671445-images (9).jpg","quotes":"There is no law except the law that there is no law."},{"quotes_image":"1529670584-images (15).jpg","quotes":"You may hold my hand for a while, but you hold my heart forever."},{"quotes_image":"1529670562-download (1).jpg","quotes":"Falsity in intellectual action is intellectual immorality."},{"quotes_image":"1529670526-images (19).jpg","quotes":"Curiosity about life in all of its aspects, I think, is still the secret of great creative people."},{"quotes_image":"1529670498-images.jpg","quotes":"You can\u2019t wait for inspiration, you have to go after it with a club."},{"quotes_image":"1529670479-images (13).jpg","quotes":"The chief enemy of creativity is good sense. Pablo Picasso Click to tweet."},{"quotes_image":"1529670408-images (19).jpg","quotes":"Curiosity about life in all of its aspects, I think, is still the secret of great creative people."},{"quotes_image":"1529670385-images (12).jpg","quotes":"The more that you read, the more things you will know, the more that you learn, the more places you\u2019ll go."},{"quotes_image":"1529670359-navigation_cover.jpg","quotes":"Falsity in intellectual action is intellectual immorality."},{"quotes_image":"1529670338-images (18).jpg","quotes":"Yesterday is history, tomorrow is a mystery, today is God\u2019s gift, that\u2019s why we call it the present."},{"quotes_image":"1529670312-download (1).jpg","quotes":"Guys love girls who have lovely smiles and lovely eyes, that is the truth behind all of it."},{"quotes_image":"1529670285-images (8).jpg","quotes":"There is no way I am not going to be the person I am not meant to, beautiful eyes, I tell you."},{"quotes_image":"1529670263-images (13).jpg","quotes":"If only we could pull out our brain and use only our eyes."},{"quotes_image":"1529670233-images (2).jpg","quotes":"Setting goals is the first step in turning the invisible into the visible."},{"quotes_image":"1529670213-images (14).jpg","quotes":"The most beautiful gift of nature is that it gives one pleasure to look around and try to comprehend what we see."},{"quotes_image":"1529670186-download (4).jpg","quotes":"Hold fast to dreams, for if dreams die, life is a broken-winged bird that cannot fly."},{"quotes_image":"1529670169-d.jpg","quotes":"A dream doesn't become reality through magic; it takes sweat, determination and hard work."},{"quotes_image":"1529670148-b.jpg","quotes":"It is better to fail in originality than to succeed in imitation."},{"quotes_image":"1529670127-d.jpg","quotes":"Success is not final; failure is not fatal: It is the courage to continue that counts."}]
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
         * quotes_image : null
         * quotes : positive action combined with positive thinking results in success
         */

        private Object quotes_image;
        private String quotes;

        public Object getQuotes_image() {
            return quotes_image;
        }

        public void setQuotes_image(Object quotes_image) {
            this.quotes_image = quotes_image;
        }

        public String getQuotes() {
            return quotes;
        }

        public void setQuotes(String quotes) {
            this.quotes = quotes;
        }
    }
}
