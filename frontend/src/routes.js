/*!
=========================================================
* Argon Dashboard React - v1.2.4
=========================================================

* Product Page: https://www.creative-tim.com/product/argon-dashboard-react
* Copyright 2024 Creative Tim
* Licensed under MIT (https://github.com/creativetimofficial/argon-dashboard-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================
* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
*/

import Index from "views/Index.js";
import Profile from "views/examples/Profile.js";
import Maps from "views/examples/Maps.js";
import Register from "views/examples/Register.js";
import Login from "views/examples/Login.js";
import Tables from "views/examples/Tables.js";
import Icons from "views/examples/Icons.js";
import AddCustomerDetailed from "views/examples/AddCustomerDetailed";
import PurchasePage from "views/examples/PurchasePage.js";
import ExpensePage from "views/examples/ExpensePage";
import PurchaseReturnPage from "views/examples/PurchaseReturnPage";
import ItemPage from "./views/examples/ItemPage";
import CashBankEntryPage from "views/examples/CashBankEntryPage";
import SalePage from "views/examples/SalePage";
import SalesReturnPage from "views/examples/SalesReturnPage";
import SalesReturnItemPage from "views/examples/SalesReturnItemPage"; // ✅ Add this import!
import PaymentPage from "views/examples/PaymentPage"; // ✅ Add PaymentPage import
import SupplierPage from "views/examples/SupplierPage"; // ✅ Add this import
import WarehousePage from "views/examples/WarehousePage"; // ✅ Add this import at the top
import SettingPage from "views/examples/SettingPage";
import ReportPage from "views/examples/ReportPage";

var routes = [
  {
    path: "/index",
    name: "Dashboard",
    icon: "ni ni-tv-2 text-primary",
    component: <Index />,
    layout: "/admin",
  },
  {
    path: "/icons",
    name: "Icons",
    icon: "ni ni-planet text-blue",
    component: <Icons />,
    layout: "/admin",
  },
  {
    path: "/maps",
    name: "Maps",
    icon: "ni ni-pin-3 text-orange",
    component: <Maps />,
    layout: "/admin",
  },
  {
    path: "/user-profile",
    name: "User Profile",
    icon: "ni ni-single-02 text-yellow",
    component: <Profile />,
    layout: "/admin",
  },
  {
    path: "/tables",
    name: "Tables",
    icon: "ni ni-bullet-list-67 text-red",
    component: <Tables />,
    layout: "/admin",
  },
  {
    path: "/login",
    name: "Login",
    icon: "ni ni-key-25 text-info",
    component: <Login />,
    layout: "/auth",
  },
  {
    path: "/register",
    name: "Register",
    icon: "ni ni-circle-08 text-pink",
    component: <Register />,
    layout: "/auth",
  },
  {
    path: "/customers",
    name: "Customers",
    icon: "ni ni-single-02 text-yellow",
    component: <AddCustomerDetailed />,
    layout: "/admin",
  },
  {
    path: "/purchase",
    name: "Purchases",
    icon: "ni ni-cart text-green",
    component: <PurchasePage />,
    layout: "/admin",
  },
  {
    path: "/expenses",
    name: "Expenses",
    icon: "ni ni-money-coins text-purple",
    component: <ExpensePage />,
    layout: "/admin",
  },
  {
    path: "/purchase-returns",
    name: "Purchase Returns",
    icon: "ni ni-archive-2 text-cyan",
    component: <PurchaseReturnPage />,
    layout: "/admin",
  },
  {
    path: "/items",
    name: "Items",
    icon: "ni ni-box-2 text-primary",
    component: <ItemPage />,
    layout: "/admin",
  },
  {
    path: "/cashbank",
    name: "Cash/Bank Entry",
    icon: "ni ni-credit-card text-info",
    component: <CashBankEntryPage />,
    layout: "/admin",
  },
  {
    path: "/sales",
    name: "Sales",
    icon: "ni ni-cart text-info",
    component: <SalePage />,
    layout: "/admin",
  },
  {
    path: "/sales-returns",
    name: "Sales Returns",
    icon: "ni ni-archive-2 text-green",
    component: <SalesReturnPage />,
    layout: "/admin",
  },
  {
    path: "/sales-return-items",
    name: "Sales Return Items",
    icon: "ni ni-archive-2 text-red",
    component: <SalesReturnItemPage />, // ✅ Use the new page here
    layout: "/admin",
  },

  {
  path: "/payments",
  name: "Payments",
  icon: "ni ni-money-coins text-pink",
  component: <PaymentPage />,
  layout: "/admin",
},

{
  path: "/suppliers",
  name: "Suppliers",
  icon: "ni ni-shop text-purple", // Pick an icon you like
  component: <SupplierPage />,
  layout: "/admin",
},

{
    path: "/warehouses",
    name: "Warehouses",
    icon: "ni ni-building text-orange", // ✅ Pick any Argon icon you like
    component: <WarehousePage />,
    layout: "/admin",
  },

  {
  path: "/settings",
  name: "Settings",
  icon: "ni ni-settings-gear-65 text-info",
  component: <SettingPage />,
  layout: "/admin",
},

{
  path: "/reports",
  name: "Reports",
  icon: "ni ni-chart-bar-32 text-primary",
  component: <ReportPage />,
  layout: "/admin",
},



];

export default routes;
