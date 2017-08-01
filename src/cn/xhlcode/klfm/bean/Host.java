package cn.xhlcode.klfm.bean;

public class Host {
	private String name;
	private String des;
	private String img;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Host [name=" + name + ", des=" + des + ", img=" + img + "]";
	}
	
}
