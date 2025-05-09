import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgIf} from "@angular/common";
import {Customer} from "../model/Customer.model";
import {CustomerService} from "../services/customer.service";

@Component({
  selector: 'app-new-customer',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './new-customer.component.html',
  styleUrl: './new-customer.component.css'
})
export class NewCustomerComponent implements OnInit {

  newCustomerFormGroup !: FormGroup;

  constructor(private formBuilder : FormBuilder,
              private CustomerService : CustomerService) {
  }

  ngOnInit(): void {
    // Initialization logic here
    this.newCustomerFormGroup = this.formBuilder.group({
      name: this.formBuilder.control("", [Validators.required, Validators.minLength(4)]),
      email: this.formBuilder.control("", [Validators.required, Validators.email]),
    });
  }

  protected readonly FormGroup = FormGroup;

  handleSaveCustomer() {
    let customer:Customer = this.newCustomerFormGroup.value;
    this.CustomerService.saveCustomer(customer).subscribe({
      next: (data) => {
        alert("Customer saved successfully");
        this.newCustomerFormGroup.reset();
      },
      error: (err) => {
        console.log(err);
      }
    });
  }
}
