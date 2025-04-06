import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/User.service";
import {User} from "../../model/User";
import { Router } from '@angular/router';
import {WebSocketService} from "../../service/WebSocketService";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();
  private idUser!: number;

  constructor(private userService:UserService,
              private router: Router,
              private webSocketService: WebSocketService) { }

  ngOnInit(): void {
    //this.webSocketService.initializeConnection(this.notification.bind(this));
  }

  goToRegister() {
    this.router.navigate(['/register']);
  }

  loginUser() {
    this.userService.loginUser(this.user).subscribe((data:any) => {
        alert("User login successfully");

        this.idUser = data.id;

        console.log(data.token);
        console.log(data.role);
        console.log(data.id);

      localStorage.removeItem('isAdminAuthenticated');
      localStorage.removeItem('isClientAuthenticated');

      localStorage.setItem("token", data.token);
      localStorage.setItem("role", data.role);
      localStorage.setItem("idUser", data.id);
      localStorage.setItem("userName", data.username);
      console.log("USERNAME LA LOGIN: " + data.username);

      if(data.role == "CLIENT"){
        this.router.navigateByUrl('/client');
        localStorage.setItem('isClientAuthenticated', 'true');
      }
      else {
        this.router.navigateByUrl('admin');
        localStorage.setItem('isAdminAuthenticated', 'true')
      }

    }, error => {

    })
  }


  private notification(mesaj: any):void {
   const notif = JSON.parse(mesaj.body);

   if(this.idUser == notif.idClient){
     alert("Device: " + notif.idDevice + " consume more then allowed");
   }
  }
}
