package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility;

/**
 * Created by sasiporn on 2/7/2018 AD.
 */

public class MyConstant {

    //    About URL
    private String urlAddOwner = "http://androidthai.in.th/gif/addowner.php";
    private String urlGetAllOwner = "http://androidthai.in.th/gif/getAllowner.php";         //  กด Command+n

    private String urlAddCustomer = "http://androidthai.in.th/gif/addcustomer1.php";
    private String urlGetAddCustomer = "http://androidthai.in.th/gif/getAllcustomer1.php";

    private String urlGetCustomerWhereOidShop = "http://androidthai.in.th/gif/getValueWhereOidShop.php";

    private String urlAddPrice = "http://androidthai.in.th/gif/addprice.php";
    private String urlGetAddPrice = "http://androidthai.in.th/gif/getAllprice.php";


    public String getUrlAddPrice() {
        return urlAddPrice;
    }

    public String getUrlGetAddPrice() {
        return urlGetAddPrice;
    }

    public String getUrlGetAddCustomer() {
        return urlGetAddCustomer;
    }

    public String getUrlGetCustomerWhereOidShop() {
        return urlGetCustomerWhereOidShop;
    }

    public String getUrlGetAllCustomer() {
        return urlGetAddCustomer;
    }

    public String getUrlAddCustomer() {
        return urlAddCustomer;
    }


    public String getUrlGetAllOwner() {
        return urlGetAllOwner;
    }

    public String getUrlAddOwner() {
        return urlAddOwner;
    }
}   // main class
