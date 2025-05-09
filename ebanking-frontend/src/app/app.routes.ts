import { Routes } from '@angular/router';
import {AccountsComponent} from "./accounts/accounts.component";
import {CustomersComponent} from "./customers/customers.component";
import {NewCustomerComponent} from "./new-customer/new-customer.component";
import {CustomerAccountsComponent} from "./customer-accounts/customer-accounts.component";

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'customers',
    pathMatch: 'full'
  },
  {
    path: 'accounts',component: AccountsComponent
  },
  {
    path: 'customers',component: CustomersComponent
  },
  {
    path: 'new-customer',component: NewCustomerComponent
  },
  {
    path: 'customer-accounts/:id',component: CustomerAccountsComponent
  },
  // Route pour gérer les urls non existantes
  {
    path: '**',
    redirectTo: 'customers'
  }
];
