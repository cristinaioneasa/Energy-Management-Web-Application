import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router, ActivatedRoute} from "@angular/router";

import {DeviceService} from "../../service/Device.service";

@Component({
  selector: 'app-first-page',
  templateUrl: './first-page.component.html',
  styleUrls: ['./first-page.component.css']
})
export class FirstPageComponent implements OnInit {


  ownerList:any;
  updateForm:FormGroup | undefined;

  constructor(private flightService: DeviceService,
              private formBuilder:FormBuilder,
              private router: Router,
              private activatedRouter: ActivatedRoute) { }

  ngOnInit(): void {

    this.initOwnerCarsForm();
  }

  initOwnerCarsForm(){
    this.updateForm=this.formBuilder.group({
      ownerInput:[null, Validators.required],
      carInput:[null,Validators.required]
    })
  }

}
