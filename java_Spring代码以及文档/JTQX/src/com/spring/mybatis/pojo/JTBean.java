package com.spring.mybatis.pojo;

import java.io.Serializable;
import java.util.Date;

public class JTBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String 区站号;

	private Date 入库时间;

	private String 通信方式;

	private Date 日期时间;

	private String 电源状态;

	private Integer 瞬时风速;

	private Integer 瞬时风向;

	private Integer 二分风速;

	private Integer 二分风向;

	private Integer 十分风速;

	private Integer 十分风向;

	private Integer 极大风速;

	private Integer 极大风向;

	private String 极大风速对应时间;

	private Integer 最大风速;

	private Integer 最大风向;

	private String 最大风速对应时间;

	private String 分钟雨量;

	private Integer 一小时雨量;

	private Integer 十分钟最大雨强;

	private String 最大雨强出现时间;

	private Integer 空气温度;

	private Integer 最高气温;

	private String 最高气温出现时间;

	private Integer 最低气温;

	private String 最低气温出现时间;

	private Integer 相对湿度;

	private Integer 最小湿度;

	private String 最小湿度出现时间;

	private Integer 本站气压;

	private Integer 最高气压;

	private String 最高气压出现时间;

	private Integer 最低气压;

	private String 最低气压出现时间;

	private Integer 能见度;

	private Integer 最大能见度;

	private String 最大能见度时间;

	private Integer 最小能见度;

	private String 最小能见度时间;

	private String 土壤湿度5cm;

	private String 土壤湿度10cm;

	private String 土壤湿度15cm;

	private String 土壤湿度20cm;

	private String area;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String get区站号() {
		return 区站号;
	}

	public void set区站号(String 区站号) {
		this.区站号 = 区站号;
	}

	public Date get入库时间() {
		return 入库时间;
	}

	public void set入库时间(Date 入库时间) {
		this.入库时间 = 入库时间;
	}

	public String get通信方式() {
		return 通信方式;
	}

	public void set通信方式(String 通信方式) {
		this.通信方式 = 通信方式;
	}

	public Date get日期时间() {
		return 日期时间;
	}

	public void set日期时间(Date 日期时间) {
		this.日期时间 = 日期时间;
	}

	public String get电源状态() {
		return 电源状态;
	}

	public void set电源状态(String 电源状态) {
		this.电源状态 = 电源状态;
	}

	public Integer get瞬时风速() {
		return 瞬时风速;
	}

	public void set瞬时风速(Integer 瞬时风速) {
		this.瞬时风速 = 瞬时风速;
	}

	public Integer get瞬时风向() {
		return 瞬时风向;
	}

	public void set瞬时风向(Integer 瞬时风向) {
		this.瞬时风向 = 瞬时风向;
	}

	public Integer get二分风速() {
		return 二分风速;
	}

	public void set二分风速(Integer 二分风速) {
		this.二分风速 = 二分风速;
	}

	public Integer get二分风向() {
		return 二分风向;
	}

	public void set二分风向(Integer 二分风向) {
		this.二分风向 = 二分风向;
	}

	public Integer get十分风速() {
		return 十分风速;
	}

	public void set十分风速(Integer 十分风速) {
		this.十分风速 = 十分风速;
	}

	public Integer get十分风向() {
		return 十分风向;
	}

	public void set十分风向(Integer 十分风向) {
		this.十分风向 = 十分风向;
	}

	public Integer get极大风速() {
		return 极大风速;
	}

	public void set极大风速(Integer 极大风速) {
		this.极大风速 = 极大风速;
	}

	public Integer get极大风向() {
		return 极大风向;
	}

	public void set极大风向(Integer 极大风向) {
		this.极大风向 = 极大风向;
	}

	public String get极大风速对应时间() {
		return 极大风速对应时间;
	}

	public void set极大风速对应时间(String 极大风速对应时间) {
		this.极大风速对应时间 = 极大风速对应时间;
	}

	public Integer get最大风速() {
		return 最大风速;
	}

	public void set最大风速(Integer 最大风速) {
		this.最大风速 = 最大风速;
	}

	public Integer get最大风向() {
		return 最大风向;
	}

	public void set最大风向(Integer 最大风向) {
		this.最大风向 = 最大风向;
	}

	public String get最大风速对应时间() {
		return 最大风速对应时间;
	}

	public void set最大风速对应时间(String 最大风速对应时间) {
		this.最大风速对应时间 = 最大风速对应时间;
	}

	public String get分钟雨量() {
		return 分钟雨量;
	}

	public void set分钟雨量(String 分钟雨量) {
		this.分钟雨量 = 分钟雨量;
	}

	public Integer get一小时雨量() {
		return 一小时雨量;
	}

	public void set一小时雨量(Integer 一小时雨量) {
		this.一小时雨量 = 一小时雨量;
	}

	public Integer get十分钟最大雨强() {
		return 十分钟最大雨强;
	}

	public void set十分钟最大雨强(Integer 十分钟最大雨强) {
		this.十分钟最大雨强 = 十分钟最大雨强;
	}

	public String get最大雨强出现时间() {
		return 最大雨强出现时间;
	}

	public void set最大雨强出现时间(String 最大雨强出现时间) {
		this.最大雨强出现时间 = 最大雨强出现时间;
	}

	public Integer get空气温度() {
		return 空气温度;
	}

	public void set空气温度(Integer 空气温度) {
		this.空气温度 = 空气温度;
	}

	public Integer get最高气温() {
		return 最高气温;
	}

	public void set最高气温(Integer 最高气温) {
		this.最高气温 = 最高气温;
	}

	public String get最高气温出现时间() {
		return 最高气温出现时间;
	}

	public void set最高气温出现时间(String 最高气温出现时间) {
		this.最高气温出现时间 = 最高气温出现时间;
	}

	public Integer get最低气温() {
		return 最低气温;
	}

	public void set最低气温(Integer 最低气温) {
		this.最低气温 = 最低气温;
	}

	public String get最低气温出现时间() {
		return 最低气温出现时间;
	}

	public void set最低气温出现时间(String 最低气温出现时间) {
		this.最低气温出现时间 = 最低气温出现时间;
	}

	public Integer get相对湿度() {
		return 相对湿度;
	}

	public void set相对湿度(Integer 相对湿度) {
		this.相对湿度 = 相对湿度;
	}

	public Integer get最小湿度() {
		return 最小湿度;
	}

	public void set最小湿度(Integer 最小湿度) {
		this.最小湿度 = 最小湿度;
	}

	public String get最小湿度出现时间() {
		return 最小湿度出现时间;
	}

	public void set最小湿度出现时间(String 最小湿度出现时间) {
		this.最小湿度出现时间 = 最小湿度出现时间;
	}

	public Integer get本站气压() {
		return 本站气压;
	}

	public void set本站气压(Integer 本站气压) {
		this.本站气压 = 本站气压;
	}

	public Integer get最高气压() {
		return 最高气压;
	}

	public void set最高气压(Integer 最高气压) {
		this.最高气压 = 最高气压;
	}

	public String get最高气压出现时间() {
		return 最高气压出现时间;
	}

	public void set最高气压出现时间(String 最高气压出现时间) {
		this.最高气压出现时间 = 最高气压出现时间;
	}

	public Integer get最低气压() {
		return 最低气压;
	}

	public void set最低气压(Integer 最低气压) {
		this.最低气压 = 最低气压;
	}

	public String get最低气压出现时间() {
		return 最低气压出现时间;
	}

	public void set最低气压出现时间(String 最低气压出现时间) {
		this.最低气压出现时间 = 最低气压出现时间;
	}

	public Integer get能见度() {
		return 能见度;
	}

	public void set能见度(Integer 能见度) {
		this.能见度 = 能见度;
	}

	public Integer get最大能见度() {
		return 最大能见度;
	}

	public void set最大能见度(Integer 最大能见度) {
		this.最大能见度 = 最大能见度;
	}

	public String get最大能见度时间() {
		return 最大能见度时间;
	}

	public void set最大能见度时间(String 最大能见度时间) {
		this.最大能见度时间 = 最大能见度时间;
	}

	public Integer get最小能见度() {
		return 最小能见度;
	}

	public void set最小能见度(Integer 最小能见度) {
		this.最小能见度 = 最小能见度;
	}

	public String get最小能见度时间() {
		return 最小能见度时间;
	}

	public void set最小能见度时间(String 最小能见度时间) {
		this.最小能见度时间 = 最小能见度时间;
	}

	public String get土壤湿度5cm() {
		return 土壤湿度5cm;
	}

	public void set土壤湿度5cm(String 土壤湿度5cm) {
		this.土壤湿度5cm = 土壤湿度5cm;
	}

	public String get土壤湿度10cm() {
		return 土壤湿度10cm;
	}

	public void set土壤湿度10cm(String 土壤湿度10cm) {
		this.土壤湿度10cm = 土壤湿度10cm;
	}

	public String get土壤湿度15cm() {
		return 土壤湿度15cm;
	}

	public void set土壤湿度15cm(String 土壤湿度15cm) {
		this.土壤湿度15cm = 土壤湿度15cm;
	}

	public String get土壤湿度20cm() {
		return 土壤湿度20cm;
	}

	public void set土壤湿度20cm(String 土壤湿度20cm) {
		this.土壤湿度20cm = 土壤湿度20cm;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}
