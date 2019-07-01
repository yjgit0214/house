package com.kgc.house.entity;

public class HouseCondition {
    //查询条件
    private String title; //标题
    private Integer startPrice;//开始价格
    private Integer endPrice; //结束价格
    private Integer did;//区域id
    private Integer sid;//街道id
    private Integer tid;//类型id
    private String flooa;//面积  格式0-40  转移到startFlooa和endFlooa
    private Integer startFlooa; //起始面积
    private Integer endFlooa; //结束面积

//    设置分页的属性
    private Integer page;
    private Integer pageSize=3;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartFlooa() {
        return startFlooa;
    }

    public void setStartFlooa(Integer startFlooa) {
        this.startFlooa = startFlooa;
    }

    public Integer getEndFlooa() {
        return endFlooa;
    }

    public void setEndFlooa(Integer endFlooa) {
        this.endFlooa = endFlooa;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Integer startPrice) {
        this.startPrice = startPrice;
    }

    public Integer getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Integer endPrice) {
        this.endPrice = endPrice;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getFlooa() {
        return flooa;
    }

    public void setFlooa(String flooa) {
        this.flooa = flooa;
        if (flooa!=null&&!flooa.equals("")){
            String ary[] = flooa.split("-");
            this.setStartFlooa(Integer.parseInt(ary[0]));
            this.setEndFlooa(Integer.parseInt(ary[1]));
        }
    }
}
