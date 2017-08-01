package cn.xhlcode.xmly.bean;

public class Track {
	private Long id;
	private String play_path_64;
	private String play_path_32;
	private String play_path;
	private int duration;
	private String title;
	private String nickname;
	private Long uid;
	private String waveform;
	private String upload_id;
	private String cover_url;
	private String cover_url_142;
	private String formatted_created_at;
	private Boolean is_favorited;
	private Long play_count;
	private Long comments_count;
	private Long shares_count;
	private Long favorites_count;
	private Long album_id;
	private String album_title;
	private String intro;
	private Boolean have_more_intro;
	private String time_until_now;
	private String category_name;
	private String category_title;
	private String played_secs;
	private Boolean is_paid;
	private String is_free;
	private String price;
	private String discounted_price;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlay_path_64() {
		return play_path_64;
	}
	public void setPlay_path_64(String play_path_64) {
		this.play_path_64 = play_path_64;
	}
	public String getPlay_path_32() {
		return play_path_32;
	}
	public void setPlay_path_32(String play_path_32) {
		this.play_path_32 = play_path_32;
	}
	public String getPlay_path() {
		return play_path;
	}
	public void setPlay_path(String play_path) {
		this.play_path = play_path;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getWaveform() {
		return waveform;
	}
	public void setWaveform(String waveform) {
		this.waveform = waveform;
	}
	public String getUpload_id() {
		return upload_id;
	}
	public void setUpload_id(String upload_id) {
		this.upload_id = upload_id;
	}
	public String getCover_url() {
		return cover_url;
	}
	public void setCover_url(String cover_url) {
		this.cover_url = cover_url;
	}
	public String getCover_url_142() {
		return cover_url_142;
	}
	public void setCover_url_142(String cover_url_142) {
		this.cover_url_142 = cover_url_142;
	}
	public String getFormatted_created_at() {
		return formatted_created_at;
	}
	public void setFormatted_created_at(String formatted_created_at) {
		this.formatted_created_at = formatted_created_at;
	}
	public Boolean getIs_favorited() {
		return is_favorited;
	}
	public void setIs_favorited(Boolean is_favorited) {
		this.is_favorited = is_favorited;
	}
	public Long getPlay_count() {
		return play_count;
	}
	public void setPlay_count(Long play_count) {
		this.play_count = play_count;
	}
	public Long getComments_count() {
		return comments_count;
	}
	public void setComments_count(Long comments_count) {
		this.comments_count = comments_count;
	}
	public Long getShares_count() {
		return shares_count;
	}
	public void setShares_count(Long shares_count) {
		this.shares_count = shares_count;
	}
	public Long getFavorites_count() {
		return favorites_count;
	}
	public void setFavorites_count(Long favorites_count) {
		this.favorites_count = favorites_count;
	}
	public Long getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(Long album_id) {
		this.album_id = album_id;
	}
	public String getAlbum_title() {
		return album_title;
	}
	public void setAlbum_title(String album_title) {
		this.album_title = album_title;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Boolean getHave_more_intro() {
		return have_more_intro;
	}
	public void setHave_more_intro(Boolean have_more_intro) {
		this.have_more_intro = have_more_intro;
	}
	public String getTime_until_now() {
		return time_until_now;
	}
	public void setTime_until_now(String time_until_now) {
		this.time_until_now = time_until_now;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_title() {
		return category_title;
	}
	public void setCategory_title(String category_title) {
		this.category_title = category_title;
	}
	public String getPlayed_secs() {
		return played_secs;
	}
	public void setPlayed_secs(String played_secs) {
		this.played_secs = played_secs;
	}
	public Boolean getIs_paid() {
		return is_paid;
	}
	public void setIs_paid(Boolean is_paid) {
		this.is_paid = is_paid;
	}
	public String getIs_free() {
		return is_free;
	}
	public void setIs_free(String is_free) {
		this.is_free = is_free;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDiscounted_price() {
		return discounted_price;
	}
	public void setDiscounted_price(String discounted_price) {
		this.discounted_price = discounted_price;
	}
	@Override
	public String toString() {
		return "Track [id=" + id + ", play_path_64=" + play_path_64 + ", play_path_32=" + play_path_32 + ", play_path="
				+ play_path + ", duration=" + duration + ", title=" + title + ", nickname=" + nickname + ", uid=" + uid
				+ ", waveform=" + waveform + ", upload_id=" + upload_id + ", cover_url=" + cover_url
				+ ", cover_url_142=" + cover_url_142 + ", formatted_created_at=" + formatted_created_at
				+ ", is_favorited=" + is_favorited + ", play_count=" + play_count + ", comments_count=" + comments_count
				+ ", shares_count=" + shares_count + ", favorites_count=" + favorites_count + ", album_id=" + album_id
				+ ", album_title=" + album_title + ", intro=" + intro + ", have_more_intro=" + have_more_intro
				+ ", time_until_now=" + time_until_now + ", category_name=" + category_name + ", category_title="
				+ category_title + ", played_secs=" + played_secs + ", is_paid=" + is_paid + ", is_free=" + is_free
				+ ", price=" + price + ", discounted_price=" + discounted_price + "]";
	}
	
}
