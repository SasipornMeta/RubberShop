package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility;

/**
 * Created by sasiporn on 2/7/2018 AD.
 */

public class MyConstant {

    //    About URL
    private String urlGetCustomerWhereOidShopV2 = "http://androidthai.in.th/gif/getCustomerWhereOidShop.php";
    private String urlGetAllBuyLatex = "http://androidthai.in.th/gif/getAllbuylatex1.php";
    private String urlDeleteBuyLatex = "http://androidthai.in.th/gif/deleteDataBuyLatex.php";

    private String urlGetAllBuySheet = "http://androidthai.in.th/gif/getAllbuysheet2.php";
    private String urlDeleteBuySheet = "http://androidthai.in.th/gif/deleteDataBuySheet.php";

    private String urlGetAllBuyCube = "http://androidthai.in.th/gif/getAllbuycube3.php";
    private String urlDeleteBuyCube = "http://androidthai.in.th/gif/deleteDataBuyCube.php";

    private String urlGetLatexWhereIdCustomer = "http://androidthai.in.th/gif/getBuyLatexWhereIDcustomer.php";
    private String urlGetSheetWhereIdCustomer = "http://androidthai.in.th/gif/getBuySheetWhereIDcustomer.php";
    private String urlGetCubeWhereIdCustomer = "http://androidthai.in.th/gif/getBuyCubeWhereIDcustomer.php";
    private String urlGetDepositWhereID = "http://androidthai.in.th/gif/getDepositWhereID.php";
    private String urlGetCustomerWhereName = "http://androidthai.in.th/gif/getCustomerWhereName.php";
    private String urlAddSale = "http://androidthai.in.th/gif/addsale1.php";
    private String urlAddBunCube = "http://androidthai.in.th/gif/addbuycube3.php";
    private String urlAddBuySheet = "http://androidthai.in.th/gif/addbuysheet2.php";
    private String urlAddBuyLatex = "http://androidthai.in.th/gif/addbuylatex1.php";
    private String urlGetLastPriceWhere_t_id = "http://androidthai.in.th/gif/getLastPriceWhere_t_id.php";

    private String urlAddOwner = "http://androidthai.in.th/gif/addowner.php";
    private String urlGetAllOwner = "http://androidthai.in.th/gif/getAllowner.php";         //  กด Command+n

    private String urlAddCustomer = "http://androidthai.in.th/gif/addcustomer1.php";
    private String urlGetAddCustomer = "http://androidthai.in.th/gif/getAllcustomer1.php";
    private String urlDeleteCustomer = "http://androidthai.in.th/gif/deleteDatacustomer.php";
    private String urlEditCustomer = "http://androidthai.in.th/gif/editColumncustomer1.php";

    private String urlGetCustomerWhereOidShop = "http://androidthai.in.th/gif/getValueWhereOidShop.php";

    private String urlAddPrice = "http://androidthai.in.th/gif/addprice.php";
    private String urlGetAddPrice = "http://androidthai.in.th/gif/getAllprice.php";

    private String urlGetAllDeposit = "http://androidthai.in.th/gif/getAlldeposit.php";
    private String urlDeleteDeposit = "http://androidthai.in.th/gif/deleteDataDeposit.php";

    //    Array
    private String[] columnCustomer = new String[]{"c_id", "c_name",
            "c_lname", "c_address", "c_tel", "c_user", "c_password", "o_idshop"};





//    Getter


    public String getUrlDeleteBuyCube() {
        return urlDeleteBuyCube;
    }

    public String getUrlDeleteBuySheet() {
        return urlDeleteBuySheet;
    }

    public String getUrlDeleteBuyLatex() {
        return urlDeleteBuyLatex;
    }

    public String getUrlDeleteDeposit() {
        return urlDeleteDeposit;
    }

    public String getUrlGetAllDeposit() {
        return urlGetAllDeposit;
    }

    public String getUrlGetCustomerWhereOidShopV2() {
        return urlGetCustomerWhereOidShopV2;
    }

    public String getUrlGetAllBuyLatex() {
        return urlGetAllBuyLatex;
    }

    public String getUrlGetAllBuySheet() {
        return urlGetAllBuySheet;
    }

    public String getUrlGetAllBuyCube() {
        return urlGetAllBuyCube;
    }

    public String getUrlGetLatexWhereIdCustomer() {
        return urlGetLatexWhereIdCustomer;
    }

    public String getUrlGetSheetWhereIdCustomer() {
        return urlGetSheetWhereIdCustomer;
    }

    public String getUrlGetCubeWhereIdCustomer() {
        return urlGetCubeWhereIdCustomer;
    }

    public String getUrlGetDepositWhereID() {
        return urlGetDepositWhereID;
    }

    public String[] getColumnCustomer() {
        return columnCustomer;
    }

    public String getUrlGetCustomerWhereName() {
        return urlGetCustomerWhereName;
    }

    public String getUrlAddSale() {
        return urlAddSale;
    }

    public String getUrlAddBunCube() {
        return urlAddBunCube;
    }

    public String getUrlAddBuySheet() {
        return urlAddBuySheet;
    }

    public String getUrlAddBuyLatex() {
        return urlAddBuyLatex;
    }

    public String getUrlGetLastPriceWhere_t_id() {
        return urlGetLastPriceWhere_t_id;
    }

    public String getUrlDeleteCustomer() {
        return urlDeleteCustomer;
    }

    public String getUrlEditCustomer() {
        return urlEditCustomer;
    }


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
