import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../../model/User";
import {UserService} from "../../service/User.service";
import {Device} from "../../model/Device";
import {DeviceService} from "../../service/Device.service";
import { WebSocketService } from "../../service/WebSocketService"

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})

export class AdminComponent implements OnInit {

  device: Device = new Device();
  user: User = new User();
  usersList: User[] = [];
  updateForm: FormGroup | undefined;
  devicesList: Device[] = [];
  showInsertDeviceSection: boolean = false;
  showDeleteDeviceSection: boolean = false;
  showDeleteUserSection: boolean = false;
  showDeviceList: boolean = false;
  showClientList: boolean = false;

  constructor(private userService: UserService,
              private deviceService: DeviceService,
              private formBuilder:FormBuilder,
              private webSocketService: WebSocketService) {

    this.showInsertDeviceSection = false;
    this.showDeleteDeviceSection = false;
    this.showClientList = false;
    this.showDeviceList = false;
    this.showDeleteUserSection = false;
  }

  ngOnInit(): void {
   /* if (this.webSocketService) {
      this.webSocketService.initializeConnectionChat(this.chatNotification.bind(this));
    }

    */
  }

  private chatNotification(notification: any): void {

  }


  insertDevice(){
    this.deviceService.insertDevice(this.device).subscribe(data=>{
      console.log(this.device.name)
      alert("Device inserted successfully");
    }, error => {alert("Insert failed");
    })
  }

  deleteDevice(id: any){
    this.deviceService.deleteDevice(id).subscribe(data=>{
      alert("Device deleted successfully");
    }, error => {alert("Deleted failed");
    })
  }

  findAll() {

    this.showClientList = ! this.showClientList
    console.log("verificare buton");
    const token = localStorage.getItem('token')
    console.log(token);
    if (token) {
    this.userService.findAllUsers(token).subscribe(
      (data2) => {
        this.usersList = data2; // Update usersList with the data received from the service
      },
      (error) => {
        console.error("Error while fetching users:", error);
      }
    );
  }
  }

  findAllDevices() {
    this.showDeviceList = ! this.showDeviceList
    this.deviceService.findAllDevices().subscribe((res)=>{
        console.log(res);
        this.devicesList = res;
      },
      (_error)=>{
      });
  }

  deleteUser(id: any) {
    this.showDeleteUserSection = true;
    const token = localStorage.getItem('token');
    if (token) {
      console.log(id);
      this.userService.deleteUser(token, id).subscribe(data => {
        alert("Client deleted successfully");
      }, error => {
        alert("Deleted failed");
      })
    }
  }
}
