package database;

import java.io.Serializable;

public class ReciptMid implements Serializable{

	private String flv;
	private String siz;
	private String ice;
	private String con;


	public ReciptMid() {


	}

	public ReciptMid(String flv, String siz, String ice, String con) {
		this.flv = flv;
		this.siz = siz;
		this.ice = ice;
		this.con = con;

	}

	//フレーバー
	public String getFlv() {
		return flv;
	}
	public void  setFlv(String flv) {
		this.flv = flv;
	}

	//サイズ
	public String getSiz() {
		return siz;
	}
	public void  setSiz(String siz) {
		this.siz = siz;
	}

	//アイス（シングル等）
	public String getIce() {
		return ice;
	}
	public void  setIce(String ice) {
		this.ice = ice;
	}

	//カップ
	public String getCon() {
		return con;
	}
	public void  setCon(String con) {
		this.con = con;
	}














}
















