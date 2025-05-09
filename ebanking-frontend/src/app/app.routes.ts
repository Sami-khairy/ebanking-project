import { Routes } from '@angular/router';
import {AccountsComponent} from "./accounts/accounts.component";
import {CustomersComponent} from "./customers/customers.component";
import {NewCustomerComponent} from "./new-customer/new-customer.component";

export const routes: Routes = [
  {
    path: 'accounts',component: AccountsComponent
  },
  {
    path: 'customers',component: CustomersComponent
  },
  {
    path: 'new-customer',component: NewCustomerComponent
  }
];
