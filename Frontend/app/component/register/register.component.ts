import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/User.service';
import {User} from '../../model/User';

@Component({
  selector: 'app-register',
  templateUrl: 'register.component.html',
  styleUrls: ['register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User = new User();
  pass: String = "";

  constructor(
    private userService: UserService,
  ) {
  }

  ngOnInit(): void {
  }

  createUser() {
    this.userService.insertUser(this.user.username).subscribe(
    )
    console.log(this.user.username)
    this.userService.register(this.user).subscribe(
      (data) => {
        alert('User created successfully');
      },
      (error) => {
        alert('User creation failed');
      }
    );
  }

}
